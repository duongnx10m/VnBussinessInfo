package com.vn.company;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vn.company.api.Urls;
import com.vn.company.fragment.FrgCompany;
import com.vn.company.model.City;
import com.vn.company.model.CityResult;
import com.vn.company.model.District;
import com.vn.company.utils.Utils;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by duongnx on 1/10/17.
 */

public class SearchActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private Spinner spinnerCity;
    private Spinner spinnerDistrict;
    private EditText etSearch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        spinnerCity = (Spinner) findViewById(R.id.spinnerCity);
        spinnerCity.setOnItemSelectedListener(this);
        spinnerDistrict = (Spinner) findViewById(R.id.spinnerTownship);
        etSearch = (EditText) findViewById(R.id.etSearch);
        findViewById(R.id.btBack).setOnClickListener(this);
        findViewById(R.id.btSearch).setOnClickListener(this);
        callApiCities();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btBack:
                if (getSupportFragmentManager().getBackStackEntryCount() > 1)
                    getSupportFragmentManager().popBackStack();
                else finish();
                break;
            case R.id.btSearch:
                int adsCount = MyApplication.getInstance().getAdsCount() + 1;
                MyApplication.getInstance().setAdsCount(adsCount);
                if (adsCount % 3 == 0)
                    showAds();
                search();
                Utils.hideKeyboard(this);
                break;
        }
    }

    private void search() {
        String search = etSearch.getText().toString();
        String url = Urls.GET_COMPANY;

        if (!TextUtils.isEmpty(search)) {
            try {
                search = URLEncoder.encode(search, "UTF-8");
                url += "&k=" + search;
            } catch (IOException e) {
            }
        }
        if (spinnerDistrict.getSelectedItem() != null) {
            District district = (District) spinnerDistrict.getSelectedItem();
            if (district.getID() != -1) {
                url += "&l=" + district.getSolrID();
            } else {
                if (spinnerCity.getSelectedItem() != null) {
                    City city = (City) spinnerCity.getSelectedItem();
                    if (city.getID() != -1) {
                        url += "&l=" + city.getSolrID();
                    }
                }
            }
        } else {
            if (spinnerCity.getSelectedItem() != null) {
                City city = (City) spinnerCity.getSelectedItem();
                if (city.getID() != -1) {
                    url += "&l=" + city.getSolrID();
                }
            }
        }
        // clear all fragment before add
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FrgCompany frgCompany = new FrgCompany();
        frgCompany.setApiUrl(url);
        changeMenu(frgCompany);
    }

    private void callApiCities() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Urls.GET_CITY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!TextUtils.isEmpty(response)) {
                    Gson gson = new Gson();
                    CityResult cityResult = gson.fromJson(response, CityResult.class);
                    if (cityResult != null) {
                        if (cityResult.getLtsItem() != null) {
                            ArrayList<City> cities = cityResult.getLtsItem();
                            City all = new City();
                            all.setTitle(getString(R.string.canuoc));
                            all.setID(-1);
                            cities.add(0, all);
                            ArrayAdapter<City> adapter = new ArrayAdapter<City>(SearchActivity.this, R.layout.spinner_item, cities);
                            adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
                            spinnerCity.setAdapter(adapter);
                        }

                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("duongnx", "error");
            }
        });
        requestQueue.add(stringRequest);
    }


    private void callApiDistricts(int districtId) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, String.format(Urls.GET_DISTRICT, districtId + ""), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!TextUtils.isEmpty(response)) {
                    Gson gson = new Gson();
                    ArrayList<District> districts = gson.fromJson(response, new TypeToken<ArrayList<District>>() {
                    }.getType());
                    if (districts != null) {
                        District district = new District();
                        district.setID(-1);
                        district.setTitle(getString(R.string.tatca));
                        districts.add(0, district);
                        ArrayAdapter<District> adapter = new ArrayAdapter<District>(SearchActivity.this, R.layout.spinner_item, districts);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerDistrict.setAdapter(adapter);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("duongnx", "error");
            }
        });
        requestQueue.add(stringRequest);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        Object data = adapterView.getItemAtPosition(position);
        if (data instanceof City) {
            City city = (City) data;
            if (city != null) {
                if (city.getID() == -1) {
                    spinnerDistrict.setAdapter(null);
                } else
                    callApiDistricts(city.getID());
            }
        } else {

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void hideSearchView() {
        spinnerCity.setVisibility(View.GONE);
        spinnerDistrict.setVisibility(View.GONE);
        etSearch.setVisibility(View.INVISIBLE);
        findViewById(R.id.btSearch).setVisibility(View.INVISIBLE);
    }

    public void showSearchView() {
        spinnerCity.setVisibility(View.VISIBLE);
        spinnerDistrict.setVisibility(View.VISIBLE);
        etSearch.setVisibility(View.VISIBLE);
        findViewById(R.id.btSearch).setVisibility(View.VISIBLE);

    }

    @Override
    public void onBackStackChanged() {
        if (getSupportFragmentManager().getBackStackEntryCount() <= 1) {
            showSearchView();
        } else {
            hideSearchView();
        }
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1)
            super.onBackPressed();
        else finish();
    }

    public void showAds() {
        if (Utils.isConnected(SearchActivity.this) && MyApplication.getInstance().isShowFull()) {
            final InterstitialAd mInterstitialAd = new InterstitialAd(SearchActivity.this);
            mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
            AdRequest adRequest = new AdRequest.Builder().addTestDevice("BB3AC6315C2254434B39A373C29C530C").addTestDevice("C51683B112491EA84E0DC1E60179783B").build();
            mInterstitialAd.loadAd(adRequest);
            mInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {
                    super.onAdLoaded();
                    mInterstitialAd.show();
                }
            });
        }
    }
}
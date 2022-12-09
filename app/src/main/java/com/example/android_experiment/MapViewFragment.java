package com.example.android_experiment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.model.LatLng;
import com.example.android_experiment.data.HttpDataLoader;
import com.example.android_experiment.data.ShopLocation;

import java.util.List;


public class MapViewFragment extends Fragment {

    private MapView mapView;
    public MapViewFragment() {
        // Required empty public constructor
    }


    public static MapViewFragment newInstance() {
        MapViewFragment fragment = new MapViewFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.map_fragment_tab, container, false);
        mapView=rootView.findViewById(R.id.bmapview);

        MapStatus.Builder builder = new MapStatus.Builder();
        builder.zoom(18.0f);
        mapView.getMap().setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));

        LatLng cenpt = new LatLng(22.255925,113.541112);
        //定义地图状态
        MapStatus mMapStatus = new MapStatus.Builder()
                .target(cenpt)
                .zoom(18)
                .build();

        mapView.getMap().setMapStatus(MapStatusUpdateFactory.newMapStatus(mMapStatus));

        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpDataLoader dataLoader=new HttpDataLoader();
                String shopJsonData= dataLoader.getHttpData("http://file.nidama.net/class/mobile_develop/data/bookstore2022.json");
                List<ShopLocation> locations=dataLoader.ParseJsonData(shopJsonData);

                MapViewFragment.this.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        AddMarkersOnMap(locations);
                    }
                });
            }
        }).start();


        mapView.getMap().setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Toast.makeText(MapViewFragment.this.getContext(), "Marker clicked", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        return rootView;
    }

    private void AddMarkersOnMap(List<ShopLocation> locations) {
        BitmapDescriptor bitmap= BitmapDescriptorFactory.fromResource(R.drawable.ic_action_home);
        for (ShopLocation shop: locations) {
            LatLng shopPoint = new LatLng(shop.getLatitude(),shop.getLongitude());

            OverlayOptions options = new MarkerOptions().position(shopPoint).icon(bitmap);
            //将maker添加到地图
            mapView.getMap().addOverlay(options);
            mapView.getMap().addOverlay(new TextOptions().bgColor(0xAAFFFF00)
                    .fontSize(32)
                    .fontColor(0xFFFF00FF).text(shop.getName()).position(shopPoint));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mapView.onResume();
    }
    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mapView.onPause();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mapView.onDestroy();
    }
}

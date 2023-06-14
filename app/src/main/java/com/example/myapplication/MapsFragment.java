package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsFragment extends Fragment {

    ArrayList<Marker> arrayList = new ArrayList<Marker>();


    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            LatLng sydney = new LatLng(-34, 151);
            LatLng latLng1 = new LatLng(22.391920488711786, 104.10134916725941);
            LatLng latLng2 = new LatLng(21.981765981415684, 104.68839543859889);
            LatLng latLng3 = new LatLng(21.723601293998467, 104.88164336268325);
            LatLng latLng4 = new LatLng(21.545515428969956, 107.69299896890115 );
            LatLng latLng5 = new LatLng(21.15545148220001, 107.37666577866963);
            LatLng latLng6 = new LatLng(20.03775071409709, 105.48757743137159);
            LatLng latLng7 = new LatLng(19.396045022839296, 105.7192580716957);
            LatLng latLng8 = new LatLng(18.686001869516765, 105.7157205493691);
            LatLng latLng9 = new LatLng(17.942752026488225, 106.45974747358017);
            LatLng latLng10 = new LatLng(17.446327761662623, 106.65158717093456);

            Marker marker1 = new Marker("Trạm Thu Phí Xuân Giao(IC17)",latLng1);
            Marker marker2 = new Marker("Trạm Thu Phí Mậu A (IC14)",latLng2);
            Marker marker3 = new Marker("Trạm Thu Phí IC12",latLng3);
            Marker marker4 = new Marker("Trạm Thu Phí Hải Hà ",latLng4);
            Marker marker5 = new Marker("Trạm Thu Phí Đoàn Kết",latLng5);
            Marker marker6 = new Marker("BOT Cầu Phao",latLng6);
            Marker marker7 = new Marker("Trạm Thu Phí Hoàng Mai",latLng7);
            Marker marker8 = new Marker("Trạm Thu Phí Bến Cầu Thủy",latLng8);
            Marker marker9 = new Marker("Trạm Thu Phí TASCO Quảng Bình ",latLng9);
            Marker marker10 = new Marker("Trạm Thu Phí BOT Quán Hàu",latLng10);

            arrayList.add(marker1);
            arrayList.add(marker2);
            arrayList.add(marker3);
            arrayList.add(marker4);
            arrayList.add(marker5);
            arrayList.add(marker6);
            arrayList.add(marker7);
            arrayList.add(marker8);
            arrayList.add(marker9);
            arrayList.add(marker10);
            int i;
            for(i=0;i< arrayList.size();i++)
            {
                googleMap.addMarker(new MarkerOptions().position(arrayList.get(i).getLatLng()).title(arrayList.get(i).getTitle()));
                googleMap.animateCamera(CameraUpdateFactory.zoomTo(15.0f));
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(arrayList.get(i).getLatLng()));
            }

            googleMap.addMarker(new MarkerOptions().position(sydney).title("sýney"));
            googleMap.animateCamera(CameraUpdateFactory.zoomTo(15.0f));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}
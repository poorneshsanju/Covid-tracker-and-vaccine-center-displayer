package com.example.covistat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import java.util.List;

public class VaccineAdaptor extends ArrayAdapter<VaccineModel> {
    private Context context;
    private List<VaccineModel> vaccineModelList;
    private List<CountryModel> countryModelsListFiltered;

    public VaccineAdaptor(Context context,List<VaccineModel>  vaccineModelList){
        super(context, R.layout.vaccine_center_item,vaccineModelList);
        this.vaccineModelList=vaccineModelList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_vaccine,null,true);

        TextView centerNameTV = view.findViewById(R.id.idCenterName);
        TextView centerAddressTV = view.findViewById(R.id.idCenterAddress);
        TextView centerTimingsTV = view.findViewById(R.id.idCenterTimings);
        TextView  vaccineNameTV = view.findViewById(R.id.idVaccineName);
        TextView ageLimitTV = view.findViewById(R.id.idAgeLimit);
        TextView availabilityTV = view.findViewById(R.id.idAvaliablity);
        TextView feeTypeTV = view.findViewById(R.id.idFeeType);

        String centerName=vaccineModelList.get(position).getCenterName();
        String centerAdress=vaccineModelList.get(position).getCenterAddress();
        String centerTimings= "From : " + vaccineModelList.get(position).getCenterFromTime() + "To :" + vaccineModelList.get(position).getCenterToTime();
        String vaccineName=vaccineModelList.get(position).getVaccineName();
        int centerAgeLimit =vaccineModelList.get(position).getAgeLimit();
        String feeType=vaccineModelList.get(position).getFeeType();
        int avilability =vaccineModelList.get(position).getAvailableCapacity();

        centerNameTV.setText(centerName);
        centerAddressTV.setText(centerAdress);
        centerTimingsTV.setText(centerTimings);
        vaccineNameTV.setText(vaccineName);
        ageLimitTV.setText(centerAgeLimit);
        feeTypeTV.setText(feeType);
        availabilityTV.setText(avilability);
        return view;


    }

    @Override
    public int getCount() {
        return vaccineModelList.size();
    }

    @Nullable
    @Override
    public VaccineModel getItem(int position) {
        return vaccineModelList.get(position);
    }

    @Nullable
    @Override
    public long getItemId(int position) {
        return position;
    }
}

package com.example.covistat;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class VaccineActivity extends AppCompatActivity {

    private Button searchBtn;
    private EditText editTextPincode;
    private ListView centersRv;
    private VaccineAdaptor vaccineAdaptor;
    public List<VaccineModel> centerList = new ArrayList<>();
    public ProgressBar loadingPB;


    DatePickerDialog picker;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine);

        searchBtn=findViewById(R.id.idBtnSearch);
        editTextPincode=findViewById(R.id.idEdtPinCode);
        centersRv=findViewById(R.id.centersRV);
        loadingPB = findViewById(R.id.idPBLoading);

        //after entering pin code and press search button
        searchBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String pinCode= editTextPincode.getText().toString();

                if(pinCode.length() != 6){

                    Toast toast= Toast.makeText(VaccineActivity.this ,"not", Toast.LENGTH_SHORT);
                    toast.show();

                }
                else{
                   centerList.clear();
                    Calendar calendar=Calendar.getInstance();
                    int year= (  calendar.get(Calendar.YEAR));
                    int month=( calendar.get(Calendar.MONTH));
                    int day=calendar.get(calendar.DAY_OF_MONTH);


                    //Date pickerDialog design
                    picker = new DatePickerDialog(VaccineActivity.this,
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                    loadingPB.setVisibility(View.VISIBLE);
                                    String date= dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                                    getAppointment(pinCode,date);
                                }
                            }, year, month, day);
                    picker.show();


                }
            }
        });

    }
    public void getAppointment(String pinCode, String date) {
        String url="https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByPin?pincode=" + pinCode + "&date=" + date;
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {


                    @Override
                    public void onResponse(String response) {
                        loadingPB.setVisibility(View.GONE);
                        try {
                            JSONObject js = new JSONObject(response);
                            String stringy = js.getString("sessions");


                            JSONArray jsonArray = new JSONArray(stringy);

                            //If there is no Centeners found
                            if(jsonArray.length() == 0){

                                Toast toast= Toast.makeText(VaccineActivity.this, "No Centers found", Toast.LENGTH_SHORT);
                                toast.show();
                            }

                            //Parse through Json Array

                            for (int i=0; i <jsonArray.length(); i++){
                                JSONObject centerObj= jsonArray.getJSONObject(i);
                                String centerName= centerObj.getString("name");
                                String centerAddress = centerObj.getString("address");
                                String centerFromTime = "1";
                                String centerToTime ="1";
                                String fee_type = centerObj.getString("fee");

                                // variable for seseion
                                JSONObject sesssionObj = centerObj.getJSONArray("sessions").getJSONObject(0);
                                int ageLimit = 18;
                                String vaccineName=centerObj.getString("vaccine");
                                int avaliableCapacity = centerObj.getInt("available_capacity");

                                //Add to adapter
                                VaccineModel vaccineModel = new VaccineModel(centerName, centerAddress, centerFromTime, centerToTime, fee_type, ageLimit, vaccineName, avaliableCapacity);
                                centerList.add(vaccineModel);


                            }
                            vaccineAdaptor = new VaccineAdaptor(VaccineActivity.this,centerList);
                            centersRv.setAdapter(vaccineAdaptor);
                            vaccineAdaptor.notifyDataSetChanged();



                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast toast= Toast.makeText(getApplicationContext(),"Error in Response", Toast.LENGTH_SHORT);
                            toast.show();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(VaccineActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

     queue.add(request);
    }
}

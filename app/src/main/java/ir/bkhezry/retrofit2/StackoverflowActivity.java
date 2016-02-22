package ir.bkhezry.retrofit2;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import java.io.IOException;


import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ir.bkhezry.retrofit2.Model.Question;
import ir.bkhezry.retrofit2.Service.APIService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class StackoverflowActivity extends AppCompatActivity {
    @Bind(R.id.page)
    EditText page;
    @Bind(R.id.pageSize)
    EditText pagesize;
    @Bind(R.id.order)
    EditText order;
    @Bind(R.id.sort)
    EditText sort;
    @Bind(R.id.tagged)
    EditText tagged;
    @Bind(R.id.site)
    EditText site;
    @Bind(R.id.getData)
    Button getData;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.response)
    TextView responseTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stackoverflow);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.getData)
    void getDataFunction() {
        getData.setText("Waiting...");
        progressBar.setVisibility(View.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.stackexchange.com/2.2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        int pageVar = Integer.parseInt(page.getText().toString());
        int pagesizeVar = Integer.parseInt(pagesize.getText().toString());
        String orderVar = order.getText().toString();
        String sortVar = sort.getText().toString();
        String taggedVar = tagged.getText().toString();
        String siteVar = site.getText().toString();
        Call<Question> call = apiService.getQuestionsService(pageVar, pagesizeVar, orderVar, sortVar, taggedVar, siteVar);
        call.enqueue(new Callback<Question>() {
            @Override
            public void onResponse(Call<Question> call, Response<Question> response) {
                getData.setText("Get Data");
                progressBar.setVisibility(View.INVISIBLE);
                String responseString = "Response: ";
                try {
                    if (response.isSuccess()) {
                        Toast.makeText(StackoverflowActivity.this, "Success", Toast.LENGTH_LONG).show();

                        Question question = response.body();
                        responseString += "\nSize Of Questions: " + question.getItems().size();
                        if (question.getItems().size() > 0)
                            responseString += "\nFirst item title: " + question.getItems().get(0).getTitle();

                        responseTextView.setText(responseString);
                    } else {
                        responseString += "\nFailed! : " + response.errorBody().string();
                        responseTextView.setText(responseString);
                    }
                } catch (IOException e) {
                    Log.e("bkhezry", "IOException:"+e.getMessage().toString());
                }

            }

            @Override
            public void onFailure(Call<Question> call, Throwable t) {
                getData.setText("Get Data");
                progressBar.setVisibility(View.INVISIBLE);
                if (t != null) {
                    responseTextView.setText("Response:\nOnFailure: " + t.getMessage());
                }
            }
        });
    }
}

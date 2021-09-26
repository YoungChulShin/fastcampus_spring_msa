package dev.practice.gift.infrastructure.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.practice.gift.common.response.CommonResponse;
import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Slf4j
@Component
public class RetrofitUtils {

  private static final HttpLoggingInterceptor loggingInterceptor =
      new HttpLoggingInterceptor().setLevel(Level.BODY);

  private static final OkHttpClient.Builder httpClient = new Builder()
      .addInterceptor(loggingInterceptor)
      .connectTimeout(3, TimeUnit.SECONDS)
      .readTimeout(10, TimeUnit.SECONDS);

  private static final Gson gson = new GsonBuilder()
      .setLenient()
      .create();

  public static Retrofit initRetrofit(String baseUrl) {
    return new Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(httpClient.build())
        .build();
  }

  public <T extends CommonResponse> Optional<T> responseSync(Call<T> call) {
    try {
      Response<T> execute = call.execute();
      if (execute.isSuccessful()) {
        return Optional.ofNullable(execute.body());
      } else {
        log.error("requestSync errorBoyd = {}", execute.errorBody());
        throw new RuntimeException("retrofit execute response error");
      }
    } catch (IOException e) {
      log.error(e.getMessage(), e);
      throw new RuntimeException("retrofit execute IOException");
    }
  }

  public void responseVoid(Call<Void> call) {
    try {
      Response<Void> execute = call.execute();
      if (!execute.isSuccessful()) {
        throw new RuntimeException("retrofit execute response error");
      }
    } catch (IOException e) {
      log.error(e.getMessage(), e);
      throw new RuntimeException("retrofit execute IOException");
    }
  }

}

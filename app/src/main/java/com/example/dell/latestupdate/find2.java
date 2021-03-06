package com.example.dell.latestupdate;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class find2 extends AppCompatActivity {

    TextView toolbartext2;
    ImageView imageView;
    RecyclerView recyclerView5;
    RecyclerView recyclerView6;
    RecyclerView recyclerView8;
    ArrayList<String> list = new ArrayList<>();
    ArrayList<movie> trailer = new ArrayList<>();
    ArrayList<video>videos = new ArrayList<>();
    TextView textView;
    TextView textView2;
    TextView textView3;
    com.example.dell.latestupdate.overviewAdapter overviewAdapter;
    TrailerAdapter trailerAdapter;
    similiarAdapter SimiliarAdapter;
    int ID;
    int cast_id;
    similarMovie SimiliarMovie;
    ArrayList<Similiar> similiar = new ArrayList<>();
    ArrayList<video> t = new ArrayList<>();
    UpcomingAdapter upcomingAdapter;
    RecyclerView recyclerView7;
    CastMember castMember;
    ArrayList<CastingM> casting;
    com.example.dell.latestupdate.circleAdapter circleAdapter;
    Button button;
    ImageView sharimage;
    ImageView small;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbartext2 = findViewById(R.id.toolbartext2);
        sharimage = findViewById(R.id.imageview2);
        recyclerView5 = findViewById(R.id.recyclerView5);
        recyclerView6 = findViewById(R.id.recyclerView6);
        recyclerView7 = findViewById(R.id.recyclerView7);
        imageView = findViewById(R.id.toolbarimage);
        textView = findViewById(R.id.textid);
        textView2 = findViewById(R.id.trailertext);
        textView3 = findViewById(R.id.similartext);
        recyclerView8 = findViewById(R.id.recyclerView8);
        small = findViewById(R.id.image2);


        Intent intent = getIntent();
        String s = intent.getStringExtra("ovre");
        int service = intent.getIntExtra("service", 0);
        String text = intent.getStringExtra("title");
        toolbartext2.setText(text);
        String pic = intent.getStringExtra("pic");
        ID = intent.getIntExtra("id", 0);
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + pic).fit().into(imageView);
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + pic).into(small);
        String rating = String.valueOf(intent.getFloatExtra("rating", 0));
        textView.setText("Rating" + "/" + " " + rating);
        list.add(s);
        overviewAdapter = new overviewAdapter(this, list, new com.example.dell.latestupdate.overviewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
        recyclerView5.setAdapter(overviewAdapter);
        overviewAdapter.notifyDataSetChanged();
        recyclerView5.setLayoutManager(new LinearLayoutManager(find2.this, LinearLayoutManager.VERTICAL, false));


if(service == 1) {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    MovieApi movieApi = retrofit.create(MovieApi.class);
    Call<getVideo> call = movieApi.getvideos(ID);

    call.enqueue(new Callback<getVideo>() {
        @Override
        public void onResponse(Call<getVideo> call, Response<getVideo> response) {
            getVideo getvideo = response.body();
            videos = getvideo.results;
            trailerAdapter = new TrailerAdapter(find2.this, videos, new TrailerAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {

                    Intent youtubeIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Contract.YOUTUBE_WATCH_BASE_URL + videos.get(position).key));
                    find2.this.startActivity(youtubeIntent);
                }
            });

            recyclerView6.setAdapter(trailerAdapter);
            trailerAdapter.notifyDataSetChanged();
            recyclerView6.setLayoutManager(new LinearLayoutManager(find2.this, LinearLayoutManager.HORIZONTAL, false));
        }

        @Override
        public void onFailure(Call<getVideo> call, Throwable t) {

        }
    });
}
if(service == 2)
{
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    MovieApi movieApi = retrofit.create(MovieApi.class);
    Call<getVideo> call = movieApi.getvideostv(ID);

    call.enqueue(new Callback<getVideo>() {
        @Override
        public void onResponse(Call<getVideo> call, Response<getVideo> response) {
            getVideo getvideo = response.body();
            videos = getvideo.results;
            trailerAdapter = new TrailerAdapter(find2.this, videos, new TrailerAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {

                    Intent youtubeIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Contract.YOUTUBE_WATCH_BASE_URL + videos.get(position).key));
                    find2.this.startActivity(youtubeIntent);
                }
            });
            Toast.makeText(find2.this, "int video", Toast.LENGTH_SHORT).show();
            recyclerView6.setAdapter(trailerAdapter);
            trailerAdapter.notifyDataSetChanged();
            recyclerView6.setLayoutManager(new LinearLayoutManager(find2.this, LinearLayoutManager.HORIZONTAL, false));
        }

        @Override
        public void onFailure(Call<getVideo> call, Throwable t) {

        }
    });
}

        sharimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.themoviedb.org/3/movie/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                MovieApi movieApi = retrofit.create(MovieApi.class);
                Call<External> call = movieApi.getexterids(ID);

                call.enqueue(new Callback<External>() {
                    @Override
                    public void onResponse(Call<External> call, Response<External> response) {
                        External external = response.body();
                        String ans = external.imdb_id;
                        Intent shareintent = new Intent();
                        shareintent.setAction(Intent.ACTION_SEND);
                        shareintent.setType("text/plain");
                        shareintent.putExtra(Intent.EXTRA_TEXT, "http://www.imdb.com/title/" + ans);
                        startActivity(Intent.createChooser(shareintent, "SHARE INTENT"));
                    }

                    @Override
                    public void onFailure(Call<External> call, Throwable t) {

                    }
                });
            }
        });
        if(service == 1) {
            setCastMember();
            setSimiliarMovie();
        }
        if(service == 2)
        {
            Toast.makeText(this , "tvshow", Toast.LENGTH_SHORT).show();
            setSimiliartv();
            setCastMembertv();
        }
    }

    private void setSimiliartv() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/tv/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MovieApi movieApi = retrofit.create(MovieApi.class);
        Call<similarMovie> call = movieApi.getsimilar(ID);

        call.enqueue(new Callback<similarMovie>() {
            @Override
            public void onResponse(Call<similarMovie> call, Response<similarMovie> response) {
                SimiliarMovie = response.body();
                similiar = SimiliarMovie.results;

                SimiliarAdapter = new similiarAdapter(find2.this, similiar, new similiarAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent i = new Intent(find2.this , find.class);
                        i.putExtra("rating" , similiar.get(position).vote_average);
                        i.putExtra("title", similiar.get(position).original_title);
                        i.putExtra("id", similiar.get(position).id);
                        i.putExtra("service" , 2);
                        i.putExtra("pic", similiar.get(position).backdrop_path);
                        i.putExtra("ovre", similiar.get(position).overview);
                        startActivity(i);
                    }
                });

                recyclerView8.setAdapter(SimiliarAdapter);
                SimiliarAdapter.notifyDataSetChanged();
                recyclerView8.setLayoutManager(new LinearLayoutManager(find2.this));
                recyclerView8.setLayoutManager( new LinearLayoutManager(find2.this, LinearLayoutManager.HORIZONTAL ,false));

            }

            @Override
            public void onFailure(Call<similarMovie> call, Throwable t) {

            }
        });

    }
    private void setCastMembertv() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/tv/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MovieApi movieApi = retrofit.create(MovieApi.class);
        Call<CastMember> call = movieApi.getcasttv(ID);

        call.enqueue(new Callback<CastMember>() {
            @Override
            public void onResponse(Call<CastMember> call, Response<CastMember> response) {
                castMember = response.body();
                casting = castMember.cast;
                Log.d("lalit", casting.get(1).name);
                Log.d("harish", casting.get(2).name);
                circleAdapter = new circleAdapter(find2.this, casting, new com.example.dell.latestupdate.circleAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(final int position) {
                        cast_id = casting.get(position).id;
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("https://api.themoviedb.org/3/")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                        MovieApi movieApi = retrofit.create(MovieApi.class);
                        Call<Details> call = movieApi.getDetail(cast_id);
                        call.enqueue(new Callback<Details>() {
                            @Override
                            public void onResponse(Call<Details> call, Response<Details> response) {
                                Details details =response.body();
                                Intent intent1 = new Intent(find2.this, castactivity.class);
                                intent1.putExtra("cast_id", cast_id);
                                intent1.putExtra("name" , details.name);
                                intent1.putExtra("service" , 2);
                                intent1.putExtra("age", details.birthday);
                                intent1.putExtra("birthplace", details.place_of_birth);
                                intent1.putExtra("biography", details.biography);
                                intent1.putExtra("pic", details.profile_path);
                                startActivity(intent1);
                            }

                            @Override
                            public void onFailure(Call<Details> call, Throwable t) {

                                Toast.makeText(find2.this, "data not available", Toast.LENGTH_SHORT).show();
                            }
                        });



                    }
                });

                recyclerView7.setAdapter(circleAdapter);
                circleAdapter.notifyDataSetChanged();
                recyclerView7.setLayoutManager(new LinearLayoutManager(find2.this));
                recyclerView7.setLayoutManager( new LinearLayoutManager(find2.this, LinearLayoutManager.HORIZONTAL ,false));

            }

            @Override
            public void onFailure(Call<CastMember> call, Throwable t) {

            }
        });


    }


    private void setCastMember() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/movie/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MovieApi movieApi = retrofit.create(MovieApi.class);
        Call<CastMember> call = movieApi.getCast(ID);

        call.enqueue(new Callback<CastMember>() {
            @Override
            public void onResponse(Call<CastMember> call, Response<CastMember> response) {
                castMember = response.body();
                casting = castMember.cast;
                Log.d("lalit", casting.get(1).name);
                Log.d("harish", casting.get(2).name);
                circleAdapter = new circleAdapter(find2.this, casting, new com.example.dell.latestupdate.circleAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(final int position) {
                        cast_id = casting.get(position).id;
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("https://api.themoviedb.org/3/")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                        MovieApi movieApi = retrofit.create(MovieApi.class);
                        Call<Details> call = movieApi.getDetail(cast_id);
                        call.enqueue(new Callback<Details>() {
                            @Override
                            public void onResponse(Call<Details> call, Response<Details> response) {
                                Details details = response.body();
                                Intent intent1 = new Intent(find2.this, castactivity.class);
                                intent1.putExtra("cast_id", cast_id);
                                intent1.putExtra("name", details.name);
                                intent1.putExtra("age", details.birthday);
                                intent1.putExtra("birthplace", details.place_of_birth);
                                intent1.putExtra("biography", details.biography);
                                intent1.putExtra("pic", details.profile_path);
                                startActivity(intent1);
                            }

                            @Override
                            public void onFailure(Call<Details> call, Throwable t) {

                                Toast.makeText(find2.this, "data not available", Toast.LENGTH_SHORT).show();
                            }
                        });


                    }
                });

                recyclerView7.setAdapter(circleAdapter);
                circleAdapter.notifyDataSetChanged();
                recyclerView7.setLayoutManager(new LinearLayoutManager(find2.this));
                recyclerView7.setLayoutManager(new LinearLayoutManager(find2.this, LinearLayoutManager.HORIZONTAL, false));

            }

            @Override
            public void onFailure(Call<CastMember> call, Throwable t) {

            }
        });


    }

    private void setSimiliarMovie() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/movie/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MovieApi movieApi = retrofit.create(MovieApi.class);
        Call<similarMovie> call = movieApi.getsimilar(ID);

        call.enqueue(new Callback<similarMovie>() {
            @Override
            public void onResponse(Call<similarMovie> call, Response<similarMovie> response) {
                SimiliarMovie = response.body();
                similiar = SimiliarMovie.results;

                SimiliarAdapter = new similiarAdapter(find2.this, similiar, new similiarAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent i = new Intent(find2.this, find.class);
                        i.putExtra("rating", similiar.get(position).vote_average);
                        i.putExtra("id", similiar.get(position).id);
                        i.putExtra("service" ,1);
                        i.putExtra("pic", similiar.get(position).backdrop_path);
                        i.putExtra("ovre", similiar.get(position).overview);
                        startActivity(i);
                    }
                });

                recyclerView8.setAdapter(SimiliarAdapter);
                SimiliarAdapter.notifyDataSetChanged();
                recyclerView8.setLayoutManager(new LinearLayoutManager(find2.this));
                recyclerView8.setLayoutManager(new LinearLayoutManager(find2.this, LinearLayoutManager.HORIZONTAL, false));

            }

            @Override
            public void onFailure(Call<similarMovie> call, Throwable t) {

            }
        });
    }
}


package com.nomadlab.mymove;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.nomadlab.mymove.databinding.ActivityMainBinding;

/*
*
* 뷰 바인딩은 findViewById와 비교했을 때 다음 이점이 있습니다.

Null Safety: findViewById 사용 시 Null Pointer Exception이 발생할 실수를 컴파일 타임에 잡아낼 수 있습니다.
Type Safety: findViewById 사용 시 Class Cast Exception이 발생할 실수를 컴파일 타임에 잡아낼 수 있습니다.
뷰 바인딩과 데이터 바인딩은 모두 클래스를 바인딩하여 뷰를 직접 참조할 수 있습니다. 뷰 바인딩은 데이터 바인딩보다 간단한 use case를 처리할 목적으로 설계되었고, 데이터 바인딩과 비교했을 때 다음과 같은 이점이 있습니다.

빠른 생성: 뷰 바인딩은 별도의 어노테이션 프로세싱이 필요하지 않아 컴파일 시간이 단축됩니다.
간단한 사용: 뷰 바인딩은 데이터 바인딩의 <Layout>, <data> 태그와 같은 특수한 XML 태그를 사용하지 않기 때문에, 더욱 빠르게 앱에 적용할 수 있습니다. 모듈 gradle 파일에서 허용하기만 하면, 모듈 내의 모든 레이아웃 파일에 자동으로 뷰 바인딩이 적용됩니다.
반면, 데이터 바인딩은 다음과 같은 이점이 있습니다.

layout variable, layout expression 지원: 데이터 바인딩은 레이아웃 변수, 표현식 등을 통해 XML 내에서 다이내믹 UI 콘텐츠를 표현할 수 있습니다.
Two-way Data Binding: 양방향 데이터 바인딩을 통해 뷰에서 생성된 값을 뷰 모델에 전달할 수도 있습니다.
두 기능의 장단점을 모두 이용하고 싶다면, 한 프로젝트에 뷰 바인딩과 데이터 바인딩을 모두 적용하는 것도 가능합니다. 일반적인 상황에서는 뷰 바인딩을 사용하고, 다이내믹 UI가 필요한 부분에는 데이터 바인딩을 적용할 수 있습니다.

*
*
*
* */

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    MoviesFragment moviesFragment;
    MyInfoFragment myInfoFragment;
    FragmentManager fragmentManager;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 1 추가
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initData();
        addMoviesFragment();
        addEventListener();
    }


    private void addMoviesFragment() {
        if (moviesFragment == null) {
            moviesFragment = MoviesFragment.newInstance();
        }
//        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.mainContainer, moviesFragment);
        transaction.commit();
    }

    private void addMyInfoFragment() {
        if (myInfoFragment == null) {
            myInfoFragment = MyInfoFragment.newInstance();
        }
//        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.mainContainer, myInfoFragment);
        transaction.commit();
    }

    private void initData() {
        fragmentManager = getSupportFragmentManager();
//        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView = binding.bottomNavigation;
    }

    private void addEventListener() {
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.page_1:
                        addMoviesFragment();
                        break;
                    case R.id.page_2:
                        addMyInfoFragment();
                        break;
                }
                return true;
            }
        });
    }


}
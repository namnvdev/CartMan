package vn.tlu.edu.android.cartman;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;

import androidx.core.view.MenuItemCompat;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import vn.tlu.edu.android.cartman.cart.CartActivity;
import vn.tlu.edu.android.cartman.cart.model.Cart;
import vn.tlu.edu.android.cartman.databinding.ActivityMainBinding;
import vn.tlu.edu.android.cartman.product.model.ProductRepository;
import vn.tlu.edu.android.cartman.product.adapter.ProductsAdapter;
import vn.tlu.edu.android.cartman.product.model.Product;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    ProductRepository productRepository;
    RecyclerView rvProduct;
    private Cart cart = new Cart();

    private TextView textCartItemCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        initData();

        rvProduct = binding.rvproduct;

//        LinearLayoutManager llm = new LinearLayoutManager(this);
//        llm.setOrientation(LinearLayoutManager.VERTICAL);
//        rvProduct.setLayoutManager(llm);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        rvProduct.setLayoutManager(mLayoutManager);



        ProductsAdapter rvAdapter  = new ProductsAdapter(this, this.productRepository.getProductList());
        rvProduct.setAdapter(rvAdapter);

    }
    private void initData(){
        ArrayList<Product> alProduct = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Product p = new Product(i, "ProductName" + i);
            int resID = getResId("ss_" + i%9, R.drawable.class);
            Uri imgUri = getUri(resID);
            p.setImage(imgUri);
            p.setPrice(Float.parseFloat(String.format("%.2f",new Random().nextFloat() * 1000)));
            alProduct.add(p);
        }
        this.productRepository = new ProductRepository(alProduct);

    }
    public Uri getUri (int resId){
       return Uri.parse("android.resource://"  + this.getPackageName().toString() + "/" + resId);
    }
    public static int getResId(String resName, Class<?> c) {

        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
        if (id == R.id.mnu_cart){
            Log.d("this","cart show here");
            Intent intent = new Intent(this, CartActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


}
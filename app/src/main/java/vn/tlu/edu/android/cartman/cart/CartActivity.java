package vn.tlu.edu.android.cartman.cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import vn.tlu.edu.android.cartman.cart.adapter.CartsAdapter;
import vn.tlu.edu.android.cartman.cart.model.Cart;
import vn.tlu.edu.android.cartman.databinding.ActivityCartBinding;

public class CartActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private RecyclerView rvProduct;
    private TextView tvTotal;
    private Cart cart = new Cart();
    private ActivityCartBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //setSupportActionBar(binding.toolbar);
        tvTotal = binding.tvTotal;

        rvProduct = binding.rvproduct;
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        rvProduct.setLayoutManager(mLayoutManager);

        CartsAdapter rvAdapter  = new CartsAdapter(this, this.cart);
        rvProduct.setAdapter(rvAdapter);
        tvTotal.setText(""+this.cart.getTotalPrice());
    }

    public void updateData(){

        tvTotal.setText("" + this.cart.getTotalPrice());

    }
}
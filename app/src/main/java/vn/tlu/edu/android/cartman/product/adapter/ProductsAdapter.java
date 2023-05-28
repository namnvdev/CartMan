package vn.tlu.edu.android.cartman.product.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import vn.tlu.edu.android.cartman.R;
import vn.tlu.edu.android.cartman.cart.model.Cart;
import vn.tlu.edu.android.cartman.cart.CartActivity;
import vn.tlu.edu.android.cartman.product.model.Product;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    private ArrayList<Product> products ;
    public Cart cart = new Cart();

    private Context mContext;

    public ProductsAdapter(Context context, ArrayList<Product> products) {
        this.mContext = context;
        this.products = products;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.item_product, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final  Product p = products.get(position);
        String sProductName = p.getName();
        holder.txtProductName.setText(sProductName);
        holder.txtPrice.setText(""+p.getPrice());
        holder.idIVSSImage.setImageURI(p.getImage());

//        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(view.getContext(),"click on item: "+ p.getName(), Toast.LENGTH_LONG).show();
//            }
//        });

        holder.ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addButtonClick(view, p);
            }
        });

    }

    private void addButtonClick(View view, Product p) {
//        Toast.makeText(view.getContext(),"click on item: "+ p.getName(), Toast.LENGTH_LONG).show();
        cart.addCart(p);
        showSnackbar(view, mContext.getString(R.string.add_product) + p.getName(), Snackbar.LENGTH_SHORT);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txtProductName;
        public TextView txtPrice;
        public RelativeLayout relativeLayout;

        public ImageView ivAdd;
        public ImageView idIVSSImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.txtProductName = (TextView)itemView.findViewById(R.id.idTVName);
            this.txtPrice = (TextView)itemView.findViewById(R.id.idTVPrice);
            this.ivAdd = (ImageView)itemView.findViewById(R.id.ivAdd);
            this.idIVSSImage = (ImageView)itemView.findViewById(R.id.idIVSSImage);
            this.relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relativelayout);

        }
    }

    public void showSnackbar(View view, String message, int duration)
    {
        Snackbar.make(view, message, duration).setAction("Cart", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CartActivity.class);
                mContext.startActivity(intent);
            }
        }).show();

    }

}

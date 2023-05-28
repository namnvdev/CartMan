package vn.tlu.edu.android.cartman.cart.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import vn.tlu.edu.android.cartman.product.model.Product;
import vn.tlu.edu.android.cartman.R;
import vn.tlu.edu.android.cartman.cart.CartActivity;
import vn.tlu.edu.android.cartman.cart.model.Cart;

public class CartsAdapter extends RecyclerView.Adapter<CartsAdapter.ViewHolder> {


//    public static Map<Integer, Integer> cartList = new HashMap<>();
//    public ProductRepository productRepository  = new ProductRepository();
    private Cart cart;
    private Context mContext;

    public CartsAdapter(Context context, Cart cart) {
        this.mContext = context;
        this.cart = cart;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.item_cart, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Integer pid = cart.getProductByOrder(position).getId();
        final Product p = cart.productRepository.getProduct(pid);

        String sProductName = p.getName();
        Integer amount = cart.cartList.get(pid);
        holder.txtProductName.setText(sProductName);
        holder.txtPrice.setText(""+p.getPrice());
        holder.idIVSSImage.setImageURI(p.getImage());
        holder.edAmount.setText("" + amount);
        holder.tvLineTotal.setText("" + cart.getLinePrice(p));

//        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(view.getContext(),"click on item: "+ p.getName(), Toast.LENGTH_LONG).show();
//            }
//        });

//        holder.ivAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(view.getContext(),"click on item: "+ p.getName(), Toast.LENGTH_LONG).show();
//                cart.addCart(p);
//            }
//        });

        holder.tvPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cart.addCart(p);
                Integer amount = cart.cartList.get(pid);
                holder.edAmount.setText(""+ amount );
                holder.tvLineTotal.setText("" + cart.getLinePrice(p));
                ((CartActivity)mContext).updateData();
            }
        });
        holder.tvMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cart.removeCart(p);
                Integer amount = cart.cartList.get(pid);
                holder.edAmount.setText(""+ amount );
                holder.tvLineTotal.setText("" + cart.getLinePrice(p));
                ((CartActivity)mContext).updateData();
            }


        });

    }

    @Override
    public int getItemCount() {
//        Log.d("CartAdapter", "" + cart.cartList.size());
        return cart.cartList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txtProductName;
        public TextView txtPrice;
        public TextView tvLineTotal;
        public EditText edAmount;
        public TextView tvPlus;
        public TextView tvMinus;
        public LinearLayoutCompat relativeLayout;
        public ImageView idIVSSImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.txtProductName = (TextView)itemView.findViewById(R.id.idTVName);
            this.txtPrice = (TextView)itemView.findViewById(R.id.idTVPrice);
            this.idIVSSImage = (ImageView)itemView.findViewById(R.id.idIVSSImage);
            this.relativeLayout = (LinearLayoutCompat) itemView.findViewById(R.id.llLayout);
            this.edAmount = (EditText) itemView.findViewById(R.id.amount);
            this.tvLineTotal = (TextView)itemView.findViewById(R.id.tvLineTotal);
            this.tvPlus = (TextView)itemView.findViewById(R.id.tvplus);
            this.tvMinus = (TextView)itemView.findViewById(R.id.tvminus);


        }
    }
}

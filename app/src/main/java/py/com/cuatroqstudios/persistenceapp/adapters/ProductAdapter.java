package py.com.cuatroqstudios.persistenceapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import py.com.cuatroqstudios.persistenceapp.R;
import py.com.cuatroqstudios.persistenceapp.activities.ProductActivity;
import py.com.cuatroqstudios.persistenceapp.activities.ProductFormActivity;
import py.com.cuatroqstudios.persistenceapp.db.dao.ProductDAO;
import py.com.cuatroqstudios.persistenceapp.models.Product;

/**
 * Created by manuel on 7/14/16.
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    private List<Product> productList;
    private Context context;
    private ProductDAO productDAO;
    private int REQUEST_CODE = 1;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvIdProveedor, tvNombre, tvCodigo, tvIdProduct, tvPrecio;
        private ImageView ivDelete, ivEdit;

        public MyViewHolder(View view) {
            super(view);
            tvIdProveedor = (TextView) view.findViewById(R.id.tvIdProveedor);
            tvNombre = (TextView) view.findViewById(R.id.tvNombre);
            tvCodigo = (TextView) view.findViewById(R.id.tvCodigo);
            tvIdProduct = (TextView) view.findViewById(R.id.tvIdProduct);
            tvPrecio = (TextView) view.findViewById(R.id.tvPrecio);

            ivDelete = (ImageView) view.findViewById(R.id.ivDelete);
            ivEdit = (ImageView) view.findViewById(R.id.ivEdit);
        }
    }


    public ProductAdapter(Context context, List<Product> productList, ProductDAO productDAO) {
        this.productList = productList;
        this.context = context;
        this.productDAO = productDAO;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Product product = productList.get(position);
        holder.tvIdProveedor.setText(String.valueOf(product.getIdProvider()));
        holder.tvIdProduct.setText(String.valueOf(product.getIdProduct()));
        holder.tvNombre.setText(product.getName());
        holder.tvCodigo.setText(product.getCode());
        holder.tvPrecio.setText(product.getPrice());

        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productDAO.deleteProduct(product);
                productList.remove(holder.getAdapterPosition());
                notifyDataSetChanged();
                ((ProductActivity) context).getProductsCount();
            }
        });

        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductFormActivity.class);
                intent.putExtra("typeOperation", "edit");
                intent.putExtra("product", product);
                ((ProductActivity) context).startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}

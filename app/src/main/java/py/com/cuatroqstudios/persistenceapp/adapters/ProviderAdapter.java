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
import py.com.cuatroqstudios.persistenceapp.activities.ProviderActivity;
import py.com.cuatroqstudios.persistenceapp.activities.ProviderFormActivity;
import py.com.cuatroqstudios.persistenceapp.db.dao.ProviderDAO;
import py.com.cuatroqstudios.persistenceapp.models.Provider;

/**
 * Created by manuel on 7/14/16.
 */
public class ProviderAdapter extends RecyclerView.Adapter<ProviderAdapter.MyViewHolder> {

    private List<Provider> providerList;
    private Context context;
    private ProviderDAO providerDAO;
    private int REQUEST_CODE = 1;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvIdProveedor, tvDescripcion, tvProveedor, tvRuc;
        private ImageView ivDelete, ivEdit;

        public MyViewHolder(View view) {
            super(view);
            tvIdProveedor = (TextView) view.findViewById(R.id.tvIdProveedor);
            tvDescripcion = (TextView) view.findViewById(R.id.tvDescripcion);
            tvProveedor = (TextView) view.findViewById(R.id.tvProveedor);
            ivDelete = (ImageView) view.findViewById(R.id.ivDelete);
            ivEdit = (ImageView) view.findViewById(R.id.ivEdit);
            tvRuc = (TextView) view.findViewById(R.id.tvRuc);
        }
    }


    public ProviderAdapter(Context context, List<Provider> providerList, ProviderDAO providerDAO) {
        this.providerList = providerList;
        this.context = context;
        this.providerDAO = providerDAO;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.provider_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Provider provider = providerList.get(position);
        holder.tvIdProveedor.setText(String.valueOf(provider.getIdProvider()));
        holder.tvDescripcion.setText(provider.getDescription());
        holder.tvProveedor.setText(provider.getProveedor());
        holder.tvRuc.setText(provider.getRuc());

        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                providerDAO.deleteProvider(provider);
                providerList.remove(holder.getAdapterPosition());
                notifyDataSetChanged();
                ((ProviderActivity) context).getProvidersCount();
            }
        });

        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProviderFormActivity.class);
                intent.putExtra("typeOperation","edit");
                intent.putExtra("provider", provider);
                ((ProviderActivity) context).startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return providerList.size();
    }
}

package at.fhv.popya.application.view;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import at.fhv.popya.R;
import at.fhv.popya.application.model.Message;

public class MessageAdapter extends ArrayAdapter<Message<?>> {

	private final Context _context;

	public MessageAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
		this._context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater) _context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.message_list_item, null);
		}

		Message<?> msg = getItem(position);
		if (msg != null) {
			TextView messg = (TextView) v.findViewById(R.id.message);
			TextView user = (TextView) v.findViewById(R.id.username);
			if (messg != null) {
				messg.setText(msg.getMessage().toString());
			}
			if (user != null) {
				user.setText(msg.get_user().getChatName());
			}
		}
		return v;
	}

	/**
	 * Add messages to the listview.
	 * 
	 * @param messages
	 *            The messages to add
	 */
	public void addMessages(List<Message<Object>> messages) {
		if (messages != null) {
			for (Message<Object> message : messages) {
				add(message);
			}
		}
	}
}

package at.fhv.popya.application.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import at.fhv.popya.R;
import at.fhv.popya.application.model.Message;

public class MessageAdapter extends ArrayAdapter<Message<?>> {

	private final Message<?>[] _messages;
	private final Context _context;

	public MessageAdapter(Context context, int textViewResourceId,
			Message<?>[] messages) {
		super(context, textViewResourceId, messages);
		this._messages = messages;
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

		Message<?> msg = _messages[position];
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
}
package com.codetest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.codetest.entity.Departure;
import com.codetest.entity.TypeDay;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

/**
 * ExpandableAdapter customization to handle departures by type of day
 */
public class DeparturesByDayTypeExpandableAdapter extends BaseExpandableListAdapter {

	private List<TypeDay> types = Arrays.asList(TypeDay.values());
	private Map<String, List<Departure>> departuresByDayType;

	private Context context;


	public DeparturesByDayTypeExpandableAdapter(Context context, List<Departure> departures) {
		this.context = context;
		configureData(departures);
	}

	private void configureData(List<Departure> departures) {
		this.departuresByDayType = new HashMap<String, List<Departure>>();
		for(TypeDay type : types){
			departuresByDayType.put(type.name(), new ArrayList<Departure>());
		}

		for (Departure departure: departures){
			departuresByDayType.get(departure.getCalendar()).add(departure);
		}
	}

	@Override
	public int getGroupCount() {
		return types.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return departuresByDayType.get(types.get(groupPosition).name()).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return types.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return departuresByDayType.get(types.get(groupPosition)).get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * handles with group view
	 * @param groupPosition
	 * @param isExpanded
	 * @param convertView
	 * @param parent
	 * @return
	 */
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

		if (convertView == null) {
			convertView = View.inflate(context, R.layout.group_expandable_list_view, null);
		}

		TextView group = (TextView) convertView.findViewById(R.id.groupExpandableListView);

		String groupName = this.types.get(groupPosition).name();
		group.setText(groupName);
		return convertView;
	}

	/**
	 * handles the child item view
	 * @param groupPosition
	 * @param childPosition
	 * @param isLastChild
	 * @param convertView
	 * @param parent
	 * @return
	 */
	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
			ViewGroup parent) {
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.item_list_view, null);
		}

		TextView group = (TextView) convertView.findViewById(R.id.itemExpandableListView);

		Departure departure= departuresByDayType.get(types.get(groupPosition).name()).get(childPosition);
		group.setText(departure.getTime());
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}

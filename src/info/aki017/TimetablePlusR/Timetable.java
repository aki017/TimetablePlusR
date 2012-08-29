package info.aki017.TimetablePlusR;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Timetable implements List<TimetableItem>
 * 基本的にList
 */
public class Timetable implements List<TimetableItem>{
	private static final Timetable instance = new Timetable();
	private List<TimetableItem> list = new ArrayList<TimetableItem>();
	
	public static Timetable getInstance()
	{
		return instance;
	}
	public void add(List<TimetableItem> list)
	{
		this.list.addAll(list);
	}
	public void add(Collection<TimetableItem> array)
	{
		this.list.addAll(array);
	}
	private Timetable()
	{
	}
	
	/**
	 * 指定したDirectionと同じところにいくTimetableを返す
	 * @param direction Direction
	 * @return 同じDirectionのTimetable
	 */
	public Timetable getTimetableByDirection(Direction direction)
	{
		Timetable tmp = new Timetable();
		for(TimetableItem item:list)
		{
			if (item.getDirection()==direction)
			tmp.add(item);
		}
		return tmp;
	}
	public TimetableItem get(int i)
	{
		return list.get(i);
	}
	public boolean add(TimetableItem item) {
		return list.add(item);
	}
	public void sort(TimetableItemComparator comparator) {
		Collections.sort(list,comparator);
	}
	@Override
	public void add(int location, TimetableItem object) {
		list.add(location, object);
		return;
	}
	@Override
	public boolean addAll(Collection<? extends TimetableItem> arg0) {
		return addAll(arg0);
	}
	@Override
	public boolean addAll(int arg0, Collection<? extends TimetableItem> arg1) {
		return addAll(arg0,arg1);
	}
	@Override
	public void clear() {
		list.clear();
		return;
	}
	@Override
	public boolean contains(Object object) {
		return list.contains(object);
	}
	@Override
	public boolean containsAll(Collection<?> arg0) {
		return list.containsAll(arg0);
	}
	@Override
	public int indexOf(Object object) {
		return list.indexOf(object);
	}
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}
	@Override
	public Iterator<TimetableItem> iterator() {
		return list.iterator();
	}
	@Override
	public int lastIndexOf(Object object) {
		return list.lastIndexOf(object);
	}
	@Override
	public ListIterator<TimetableItem> listIterator() {
		return list.listIterator();
	}
	@Override
	public ListIterator<TimetableItem> listIterator(int location) {
		return list.listIterator(location);
	}
	@Override
	public TimetableItem remove(int location) {
		return list.remove(location);
	}
	@Override
	public boolean remove(Object object) {
		return list.remove(object);
	}
	@Override
	public boolean removeAll(Collection<?> arg0) {
		return list.removeAll(arg0);
	}
	@Override
	public boolean retainAll(Collection<?> arg0) {
		return list.retainAll(arg0);
	}
	@Override
	public TimetableItem set(int location, TimetableItem object) {
		return list.set(location, object);
	}
	@Override
	public int size() {
		return list.size();
	}
	@Override
	public List<TimetableItem> subList(int start, int end) {
		return list.subList(start, end);
	}
	@Override
	public Object[] toArray() {
		return list.toArray();
	}
	@Override
	public <T> T[] toArray(T[] array) {
		return list.toArray(array);
	}
	/**
	 * 時刻をすぎてしまったTimetableItemを削除する<br>
	 * 一度削除したデータは復元できない
	 */
	public void update() {
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		for (Iterator<TimetableItem> iterator = list.iterator(); iterator.hasNext();) {
			TimetableItem item = (TimetableItem) iterator.next();
			if((item.getTime()-(hour*60+minute))<=-5)iterator.remove();
		}
	}
	private List<TimetableItem> getList()
	{
		return this.list;
	}
	public Timetable clone()
	{
		Timetable timetable = new Timetable();
		for(TimetableItem item:Timetable.getInstance().getList())
			timetable.add(item);
		return timetable;
	}
	public static Timetable getData() {
		return (Timetable) getInstance().clone();
	}
}

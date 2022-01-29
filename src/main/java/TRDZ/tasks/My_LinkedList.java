package TRDZ.tasks;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class My_LinkedList<Type> implements Iterable<Type> {
	private int size;
	private My_Node<Type> first;
	private My_Node<Type> last;

	private void add_start(Type element) {
		last = first = new My_Node<>(element);
		}

	void add_first(Type element) {
		size++;
		if (size==1) add_start(element);
		else first = first.prev = new My_Node<>(element, first);
		}

	void add_last(Type element) {
		size++;
		if (size==1) add_start(element);
		else last = last.next = new My_Node<>(element, null, last);
		}

	void merge(My_LinkedList<Type> list) {
		size+=list.size;
		list.first.prev = last;
		last.next = list.first;
		last = list.last;
		}

	private Type get_start() {
		My_Node<Type> getter = first;
		first = null;
		last =null;
		return getter.item;
		}

	/**
	 * Получение значение первого элемента и его удаление
	 * @return значение первого элемента
	 */
	Type get_first() {
		if (is_empty()) throw new NoSuchElementException();
		size--;
		if (is_empty()) return get_start();
		My_Node<Type> getter = first;
		first = first.next;
		first.prev = getter.next = null;
		return getter.item;
		}

	/**
	 * Получение значение последнего элемента и его удаление
	 * @return значение последнего элемента
	 */
	Type get_last()	{
		if (is_empty()) throw new NoSuchElementException();
		size--;
		if (is_empty()) return get_start();
		My_Node<Type> getter = last;
		last = last.prev;
		last.next = getter.prev = null;
		return getter.item;
		}

	/**
	 * Удаление первого элемента
	 */
	void remove_first() {
		if (is_empty()) return;
		size--;
		if (is_empty()) {
			first = null;
			last = null;
			}
		else {
			first = first.next;
			first.prev = first.prev.next = null;
			}
		}

	/**
	 * Удаление последнего элемента
	 */
	void remove_last() {
		if (is_empty()) return;
		size--;
		if (is_empty()) {
			first = null;
			last = null;
			}
		else {
			last = last.prev;
			last.next = last.next.prev = null;
			}
		}

	/**
	 * Получение значение последнего элемента
	 * @return значение последнего элемента
	 */
	Type see_first() {
		if (is_empty()) throw new NoSuchElementException();
		return first.item;
		}

	/**
	 * получение значение последнего элемента
	 * @return значение последнего элемента
	 */
	Type see_last() {
		if (is_empty()) throw new NoSuchElementException();
		return last.item;
		}

	/**
	 * Проверка на пустоту списка
	 * @return true / false
	 */
	boolean is_empty(){
		return size==0;
		}

	/**
	 * Проверка на наличие значения в списке
	 * @param elm значение
	 * @return true / false
	 */
	boolean contain(Type elm) {
		My_Node<Type> point = first;
		while (point != null) {
			if (point.item == elm) return true;
			point = point.next;
			}
		return false;
		}

	/**
	 * Удаление первого встреченого элемента с нужным значением
	 * @param elm значение для удаления
	 * @return было ли удаление успешно
	 */
	boolean remove(Type elm) {
		My_Node<Type> point = first;
		while (point != null) {
			if (point.item == elm) break;
			point = point.next;
			}
		if (point==null) return false;
		if (point==first) {remove_first(); return true;}
		if (point==last) {remove_last(); return true;}

		point.prev.next=point.next;
		point.next.prev=point.prev;
		point.next=point.prev=null;

		return true;
		}

	/**
	 * Удаление всех значений равных полученому
	 * @param elm значение для удаления
	 */
	void remove_All(Type elm) {
		while (true) {
			if (remove(elm)) remove(elm);
			else break;
			}
		}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("[");
		My_Node<Type> point = first;
		while (point != null) {
			result.append(point.item.toString());
			if (point.next != null) result.append(" <-> ");
			point = point.next;
			}
		result.append("]");
		return result.toString();
		}

	/**
	 * Вывод элементов списка с конца к началу
	 * @return список в тектсе
	 */
	public String toString_backwards()	{
		StringBuilder result = new StringBuilder("[");
		My_Node<Type> point = last;
		while (point != null) {
			result.append(point.item.toString());
			if (point.prev != null) result.append(" <-> ");
			point = point.prev;
			}
		result.append("]");
		return result.toString();
		}

	@Override
	public Iterator<Type> iterator() {
		return new My_Iterator(first);
		}

	private class My_Iterator implements Iterator<Type> {
		private My_Node<Type> last;
		private My_Node<Type> next;

		public My_Iterator(My_Node<Type> next) {
			this.next = next;
			}

		@Override
		public boolean hasNext() {
			return next!=null;
			}

		@Override
		public Type next()	{
			if (!hasNext()) throw new NoSuchElementException();
			last = next;
			next = next.next;
			return last.item;
			}
		}

	private static class My_Node<Type> {
		Type item;
		My_Node<Type> next;
		My_Node<Type> prev;

		private My_Node(Type item) {
			this.item=item;
			}

		private My_Node(Type item, My_Node<Type> next) {
			this(item);
			this.next = next;
			}

		private My_Node(Type item, My_Node<Type> next, My_Node<Type> prev) {
			this(item,next);
			this.prev = prev;
			}
		}
	}

package TRDZ.tasks;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Initialization
	{
	private static final String[] accept = {"Yes","yes","1","true","True","Да","да","+"}; 	//Задаем перечень согласия
	protected static My_LinkedList<Integer> line = new My_LinkedList<>();
	protected static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		My_line();
		System.out.println("Прекращение работы.");
		}

	/**
	 * Полный перечень взаимодействия со списком
	 */
	public static void My_line(){
		while (true) {
			int action = -1;
			Faze_base();
		//region Ввод действия
			while (!(action >-1)) {
				action=Get_action();
				if (action >11) {        	//Оформление ошибки ввода
					Faze_base();
					action =-1;}
				scanner.nextLine();
				}
		//endregion
		//region Взаимодействия
			switch (action) {
			case 1:	//Добавление х элементов
				System.out.println("Сколько добавить?");
			//region Ввод действия
				action = 0;
				while (!(action >0)) {
					action=Get_action();
					scanner.nextLine();
					}
			//endregion
				if (line.is_empty()) line = generate(action);
				else line.merge(generate(action));
				break;
			case 2:	//Добавление элемента в начало
				System.out.println("Введите число");
			//region Ввод действия
				action = -1;
					while (!(action >-1)) {
					action=Get_action();
					scanner.nextLine();
					}
			//endregion
				line.add_first(action);
				break;
			case 3:	//Добавление элемента в конец
				System.out.println("Введите число");
			//region Ввод действия
				action = -1;
				while (!(action >-1)) {
				action=Get_action();
				scanner.nextLine();
				}
			//endregion
				line.add_last(action);
				break;
			case 4:	//Взятие элемента из начала
				try	{System.out.println("Первый взят - "+line.get_first().toString());}
				catch (NoSuchElementException e) { System.out.println("Элемент не найден. Список пуст"); }
				break;
			case 5:	//Взятие элемента с конца
				try	{System.out.println("Последний взят - "+line.get_last().toString());}
				catch (NoSuchElementException e) { System.out.println("Элемент не найден. Список пуст"); }
				break;
			case 6:	//Просмотр значения элемента из начала
				try	{System.out.println("Первый элемент- "+line.see_first().toString());}
				catch (NoSuchElementException e) { System.out.println("Элемент не найден. Список пуст"); }
				break;
			case 7:	//Просмотр значения элемента из конца
				try	{System.out.println("Последний элемент- "+line.see_last().toString());}
				catch (NoSuchElementException e) { System.out.println("Элемент не найден. Список пуст"); }
				break;
			case 8:	//Поиск значения
				System.out.println("Введите число");
			//region Ввод действия
				action = -1;
				while (!(action >-1)) {
					action=Get_action();
					scanner.nextLine();
					}
			//endregion
				if (line.contain(action)) System.out.println(action+" присутсвует в списке");
				else System.out.println(action+" отсутствует в списке");
				break;
			case 9:	//Удаление элемента со значением
				System.out.println("Введите число");
			//region Ввод действия
				action = -1;
				while (!(action >-1)) {
					action=Get_action();
					scanner.nextLine();
					}
			//endregion
				if (line.remove(action)) System.out.println(action+" найдено и успешно удалено");
				else System.out.println(action+" не найдено");
				break;
			case 10:	//Удаление всех элементов со значением
				System.out.println("Введите число");
			//region Ввод действия
				action = -1;
				while (!(action >-1)) {
					action=Get_action();
					scanner.nextLine();
					}
			//endregion
				line.remove_All(action);
				System.out.println(action+" гарантированно отсутсвует");
				break;
			case 11: //Перебор элементов итератором
				System.out.println("Перебор элементов итератором");
				System.out.print("[");
				for (Object el : line) System.out.print(" "+el);
				System.out.println(" ]");
				break;
			case 0:	//Выход
				System.out.println("Вы действительно хотите уйти?");
				if (Arrays.asList(accept).contains(scanner.next())) {return;}
				}
		//endregion
			}
		}

	/**
	 * Получение номера действия
	 * @return результат действия
	 */
	private  static int Get_action() {
		int action;
		if (scanner.hasNextInt()) { action = scanner.nextInt(); } //Проверка на некоректный ввод
		else { action = -1; }
		return action;
		}

	/**
	 * Быстрый вывод текста(сценки)
	 */
	private static void Faze_base() {
		System.out.println("\n"+"Мой список с начала: "+line);
		System.out.println("Мой список с конца:  "+line.toString_backwards()+"\n");
		System.out.println("0) Закрытие программы ");
		System.out.println("1) Сгенерировать несколько дополнительных элементов");
		System.out.println("2) Добавить эначение в начало");
		System.out.println("3) Добавить эначение в конец");
		System.out.println("4) Взять элемент из начала");
		System.out.println("5) Взять элемент с конца");
		System.out.println("6) Посмотреть на элемент в начале");
		System.out.println("7) Посмотреть на элемент в конце");
		System.out.println("8) Найти есть ли в списке нужное значение");
		System.out.println("9) Удалить элемент по значению");
		System.out.println("10) Удалить все элементы со значением");
		System.out.println("11) Перебор значений итератором");
		}

	/**
	 * Генерация списка заданоо числа элементов
	 * @param total сколько элементов будет в списке
	 * @return итоговый список
	 */
	public static My_LinkedList<Integer> generate(int total) {
		My_LinkedList<Integer> line = new My_LinkedList<>();
		for (int i = 0; i < total; i++)	{
			if ((int)(Math.random()*2)>0) line.add_first((int)(Math.random()*9));
			else line.add_last((int)(Math.random()*9));
			}
		return line;
		}

	}

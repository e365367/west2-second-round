import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class West2FriedChickenRestaurant implements FriedChickenRestaurant {
	static LocalDate date = LocalDate.of(2020, 11, 22);
	static Beer qingdao = new Beer("�ൺ", 8, date);
	static Juice orjuice = new Juice("��֭", 12, date);
	static Beer baiwei = new Beer("����", 9, date);
	static Beer wusu = new Beer("����", 12, date);
	static Juice watjuice = new Juice("����֭", 10, date);
	static SetMeal meal1 = new SetMeal("�ײ�һ", 25, "ը��1", qingdao);
	static SetMeal meal2 = new SetMeal("�ײͶ�", 25, "ը��2", orjuice);

	public double balance;
	public List<Beer> beers = new ArrayList<>();
	public List<Juice> juices = new ArrayList<>();
	static final List<SetMeal> setmeals = List.of(new SetMeal("�ײ�һ", 25, "ը��1", qingdao),
			new SetMeal("�ײͶ�", 25, "ը��2", orjuice));

	public West2FriedChickenRestaurant(double balance, List<Beer> beers, List<Juice> juices, List<SetMeal> setmeals) {
		this.balance = balance;
		this.beers = beers;
		this.juices = juices;

	}

	private void use(Beer beer) {
		if (beer.isexpired() == false) {
			int flag = 0;
			for (int i = 0; i < beers.size(); i++) {
				if (beers.get(i).name == beer.name) {
					beers.remove(i);
					flag = 1;
					System.out.println("����ơ�Ƴɹ�");
					break;
				}
			}
			if (flag == 0) {
				throw new IngredientSortOutException(beer.name);
			}
		} else {
			throw new IngredientSortOutException(beer.name);
		}
	}

	private void use(Juice juice) {
		if (juice.isexpired() == false) {
			int flag = 0;
			for (int i = 0; i < juices.size(); i++) {
				if (juices.get(i).name == juice.name) {
					juices.remove(i);
					flag = 1;
					System.out.println("������֭�ɹ�");
					break;
				}
			}
			if (flag == 0) {
				throw new IngredientSortOutException(juice.name);
			}
		} else {
			throw new IngredientSortOutException(juice.name);
		}
	}

	@Override
	public void sell_SetMeal(SetMeal setmeal) {
		for (int i = 0; i < setmeals.size(); i++) {
			//System.out.println("�����ײ�");
			if (setmeal.drink instanceof Beer) {
				Beer temp = (Beer) setmeal.drink;
				use(temp);
				break;
				// System.out.println("����ơ�Ƴɹ�");
			} else if (setmeal.drink instanceof Juice) {

				Juice temp = (Juice) setmeal.drink;
				use(temp);
				break;
				// System.out.println("������֭�ɹ�");
			}

		}
	}

	@Override
	public void restock(Drinks drink, int number) {
		if (drink instanceof Beer) {
			if (number * drink.cost <= balance) {
				Beer temp = (Beer) drink;
				for (int i = 0; i < number; i++) {
					beers.add(temp);
				}
				System.out.println("����ơ�Ƴɹ�");
			} else {
				throw new OverdraftBalanceException(number * drink.cost - balance);
			}
		} else if (drink instanceof Juice) {
			if (number * drink.cost <= balance) {
				Juice temp = (Juice) drink;
				for (int i = 0; i < number; i++) {
					juices.add(temp);
				}
				System.out.println("������֭�ɹ�");
			} else {
				throw new OverdraftBalanceException();
			}
		}
	}
}

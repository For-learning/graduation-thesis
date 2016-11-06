package tsp;

public class Population {

	// 保持 路径的 集合 即为种群
	Tour[] tours;

	// Construct a population
	public Population(int populationSize, boolean initialise) {
		tours = new Tour[populationSize];
		// If we need to initialise a population of tours do so
		if (initialise) {
			// Loop and create individuals
			for (int i = 0; i < populationSize(); i++) {
				Tour newTour = new Tour();
				newTour.generateIndividual();
				saveTour(i, newTour);
			}
		}
	}

	// 获取当前种群 中 最优的个体
	public Tour getFittest() {
		Tour fittest = tours[0];
		// Loop through individuals to find fittest
		for (int i = 1; i < populationSize(); i++) {
			if (fittest.getFitness() <= getTour(i).getFitness()) {
				fittest = getTour(i);
			}
		}
		return fittest;
	}

	// Saves a tour
	public void saveTour(int index, Tour tour) {
		tours[index] = tour;
	}

	// Gets a tour from population
	public Tour getTour(int index) {
		return tours[index];
	}

	// Gets population size
	public int populationSize() {
		return tours.length;
	}
}
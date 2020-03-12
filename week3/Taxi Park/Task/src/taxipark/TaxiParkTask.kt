package taxipark

/*
 * Task #1. Find all the drivers who performed no trips.
 */
fun TaxiPark.findFakeDrivers(): Set<Driver> =
        allDrivers.subtract(trips.map { it.driver })

/*
 * Task #2. Find all the clients who completed at least the given number of trips.
 */
fun TaxiPark.findFaithfulPassengers(minTrips: Int): Set<Passenger> =
        if (minTrips == 0)
            allPassengers
        else
            trips.flatMap { it.passengers }
                    .groupBy { it }
                    .filterValues { it.size >= minTrips }
                    .keys

/*
 * Task #3. Find all the passengers, who were taken by a given driver more than once.
 */
fun TaxiPark.findFrequentPassengers(driver: Driver): Set<Passenger> =
        trips.filter { it.driver == driver }
                .flatMap { it.passengers }
                .groupBy { it }
                .filterValues { it.size > 1 }
                .keys

/*
 * Task #4. Find the passengers who had a discount for majority of their trips.
 */
fun TaxiPark.findSmartPassengers(): Set<Passenger> {
    val (discountTrips, noDiscountTrips) = trips.partition { it.discount != null }

    val passengersWithNumberOfDiscountsTrips: Map<Passenger, Int> = discountTrips.flatMap { it.passengers }
            .groupBy { it }
            .mapValues { it.value.size }

    val passengersWithNumberOfNoDiscountsTrips = noDiscountTrips.flatMap { it.passengers }
            .groupBy { it }
            .mapValues { it.value.size }

    return passengersWithNumberOfDiscountsTrips
            .filter { (passenger, numberOfDiscounts) -> numberOfDiscounts > (passengersWithNumberOfNoDiscountsTrips.get(passenger) ?: 0) }
            .keys
}

/*
 * Task #5. Find the most frequent trip duration among minute periods 0..9, 10..19, 20..29, and so on.
 * Return any period if many are the most frequent, return `null` if there're no trips.
 */
fun TaxiPark.findTheMostFrequentTripDurationPeriod(): IntRange? {
    return trips.map { it -> it.duration - it.duration % 10 } // trip (d=57) -> 50
            .map { it -> IntRange(it, it + 9) } // 50 -> 50..59
            .groupBy { it }
            .maxBy { it.value.size }
            ?.key
}

/*
 * Task #6.
 * Check whether 20% of the drivers contribute 80% of the income.
 */
fun TaxiPark.checkParetoPrinciple(): Boolean {
    if (trips.isEmpty()) {
        return false
    } else {
        val twentyPercentOfDrivers: Int = allDrivers.count() / 5;

        val incomeOf20PercentOfDrivers = trips.groupBy { it.driver }
                .map { (key, value) -> key to value.map { it.cost }.sum() }
                .sortedByDescending { it.second }
                .take(twentyPercentOfDrivers)
                .map { it.second }
                .sum()

        val eightyPercentOfIncome = trips.map { it.cost }.sum() * 0.8

        return incomeOf20PercentOfDrivers >= eightyPercentOfIncome
    }
}
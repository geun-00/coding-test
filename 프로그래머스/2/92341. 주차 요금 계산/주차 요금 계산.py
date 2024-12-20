from collections import defaultdict
from math import ceil


class Fee:
    def __init__(self, base_time, base_fee, unit_time, unit_fee):
        self.base_time = base_time
        self.base_fee = base_fee
        self.unit_time = unit_time
        self.unit_fee = unit_fee

    def calculate_cost(self, time):
        fee = self.base_fee

        exceed_time = max(0, time - self.base_time)
        fee += ceil(exceed_time / self.unit_time) * self.unit_fee

        return fee


class Car:
    def __init__(self):
        self.last_in_time = 0
        self.total_time = 0

    def car_in(self, in_time):
        self.last_in_time = in_time

    def car_out(self, out_time):
        if self.last_in_time == -1:
            return

        self.total_time += (out_time - self.last_in_time)
        self.last_in_time = -1

    def get_cost(self, fee):
        return fee.calculate_cost(self.total_time)


def parse_time(s):
    hour, minute = map(int, s.split(":"))
    return hour * 60 + minute


def solution(fees, records):
    fee = Fee(fees[0], fees[1], fees[2], fees[3])
    cars = defaultdict(lambda: Car())

    for record in records:

        times, car_number, status = record.split()
        car_time = parse_time(times)

        if status == "IN":
            cars[car_number].car_in(car_time)
        else:
            cars[car_number].car_out(car_time)

    end_time = parse_time("23:59")
    for car in cars.values():
        car.car_out(end_time)

    ans = []

    for car_number in sorted(cars):
        ans.append(cars[car_number].get_cost(fee))

    return ans

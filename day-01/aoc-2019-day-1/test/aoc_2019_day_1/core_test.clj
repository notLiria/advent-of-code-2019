(ns aoc-2019-day-1.core-test
  (:require [clojure.test :refer :all]
            [aoc-2019-day-1.core :refer :all]))

(deftest a-test
  (testing "FIXME, I fail."
    (is (= 0 0))))

(deftest calc-fuel-test
  (is (= -2 (calc-fuel 0)))
  (is (= 2 (calc-fuel 12)))
  (is (= 2 (calc-fuel 14)))
  (is (= 654 (calc-fuel 1969)))
  (is (= 33583 (calc-fuel 100756))))

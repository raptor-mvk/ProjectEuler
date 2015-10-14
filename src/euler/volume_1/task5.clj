; Topic: prime numbers

(ns
  ^{:author raptor_MVK}
  euler.volume_1.task5
  (:use clojure.test)
  (:use tools.seqs))

(defn min-multiple
  "Given N, returns the smallest positive number that is evenly divisible by all of the
  numbers from 1 to N"
  [n]
  (let [less-n (fn [coll] (take-while #(<= % n) coll))
        pow-iter (fn [n] (iterate #(* % n) n))
        prime-factors (less-n (prime-seq))
        prime-powers (map #(last (less-n (pow-iter %))) prime-factors)]
    (reduce *' prime-powers)))

(deftest test1 (is (= (min-multiple 10) 2520)))

(deftest test2 (is (= (min-multiple 20) 232792560)))

(time (run-tests 'euler.volume_1.task5))
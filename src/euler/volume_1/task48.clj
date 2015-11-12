; Topic: elementary

(ns
  ^{:author raptor_MVK}
  euler.volume_1.task48
  (:use clojure.test)
  (:use tools.core)
  (:use tools.math))

(defn self-power-sum
  "Given N, returns last ten digits of sum of 1^1+2^2+...+N^N"
  [n]
  (let [k (nat-pow 10 10)]
    (reduce #(rem (+ %1 %2) k) (map #(nat-pow-mod % % k) (rrange+ n)))))

(deftest test1 (is (= (self-power-sum 10) 405071317)))

(deftest test2 (is (= (self-power-sum 1000) 9110846700)))

(time (run-tests 'euler.volume_1.task48))

(shutdown-agents)
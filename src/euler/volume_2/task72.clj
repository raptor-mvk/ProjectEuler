; Topic: number theory
; Idea: the answer is sum of phi(n)

(ns
  ^{:author raptor_MVK}
  euler.volume_2.task72
  (:use clojure.test)
  (:use tools.factorization)
  (:use tools.core))

(defn reduced-proper-fraction-count
  "Given N, returns the number of reduced proper fractions, for which denominator <= N"
  [n]
  (let [primes (take-while #(<= % n) (prime-seq))
        add (fn [m k v] (assoc-in m [k] v))
        upd (fn [m k v] (update-in m [k] * v))
        prime-factors (loop [coll primes
                             res (reduce #(add %1 %2 %2) {} (range+ 2 n))]
                        (if (empty? coll)
                          res
                          (let [cur (first coll)
                                cur-mult (- 1 (/ 1 cur))]
                            (recur (rest coll)
                              (reduce #(upd %1 %2 cur-mult) res
                                (take-while #(<= % n) (iterate #(+ % cur) cur)))))))]
    (reduce #(+' %1 (val %2)) 0 prime-factors)))

(deftest test1 (is (= (reduced-proper-fraction-count 8) 21)))

(deftest test2 (is (= (reduced-proper-fraction-count 1000000) 303963552391)))

(time (run-tests 'euler.volume_2.task72))

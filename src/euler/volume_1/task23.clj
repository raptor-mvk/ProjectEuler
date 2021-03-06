; Topic: number theory

(ns
  ^{:author "raptor_MVK"}
  euler.volume_1.task23
  (:use clojure.test)
  (:use tools.core)
  (:use tools.seqs))

(defn non-abundant-sum
  "Returns the sum of all the positive integers which cannot be written as the sum of two
  abundant numbers"
  []
  (let [limit 28123
        abundants (abundants-seq limit)
        abundant-pairs (fn [n]
                         (map #(+ % n)
                           (take-drop-while #(< % n) #(<= % (- limit n)) abundants)))
        abundant-sums (reduce into #{} (map abundant-pairs abundants))]
    (- (* (quot (inc limit) 2) limit) (reduce + abundant-sums))))

(deftest test1 (is (= (non-abundant-sum) 4179871)))

(time (run-tests 'euler.volume_1.task23))

(shutdown-agents)
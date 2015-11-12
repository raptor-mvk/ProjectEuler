; Topic: elementary

(ns
  ^{:author raptor_MVK}
  euler.volume_1.task49
  (:use clojure.test)
  (:use tools.core)
  (:use tools.factorization)
  (:use tools.conversions))

(defn equal-dif-permut-prime-seq
  "Returns lexicographically ordered vector, containing two strings, containing
  increasing sequences of three 4-digit prime numbers, where:
   - each of the numbers in a sequence are permutations of one another
   - difference between 3rd and 2nd numbers is equal to difference between 2nd and 1st
   numbers"
  []
  (let [prime-groups (map last (filter #(> (count (last %)) 2)
                                 (group-by #(set (num2seq %))
                                   (take-drop-while #(< % 1000)
                                     #(< % 10000) (prime-seq)))))
        find-seq (fn [s] (filter #(and (> (second %) 0) (= (second %) (last %)))
                           (for [x s
                                 y (drop-while #(< % x) s)
                                 z (drop-while #(< % y) s)]
                             (vector x (- y x) (- z y)))))]
    (sort (map #(apply str %)
            (map #(reductions + (first %))
              (filter #(not (empty? %)) (map find-seq prime-groups)))))))

(deftest test1 (is (= (first (equal-dif-permut-prime-seq)) "148748178147")))

(deftest test2 (is (= (second (equal-dif-permut-prime-seq)) "296962999629")))

(time (run-tests 'euler.volume_1.task49))
; Topic: number theory
; Idea: search through pentagonal numbers for sum S, checking whether S - X,
;       S - 2 * X are pentagonal, where X moving from 1 to S / 2

(ns
  ^{:author "raptor_MVK"}
  euler.volume_1.task44
  (:use clojure.test)
  (:use tools.core)
  (:use tools.math))

(defn min-sum-diff-pentagonal
  "Returns minimal pentagonal number, that is difference of pentagonal numbers, which sum
  is a pentagonal number too"
  []
  (let [pentagonal-nums (map #(quot (* % (dec (* 3 %))) 2) (rrange))]
    (loop [used [(first pentagonal-nums)]
           nums (rest pentagonal-nums)]
      (let [cur-sum (first nums)
            test-nums (map #(vector (- cur-sum %) (- cur-sum % %))
                        (filter #(< % (quot cur-sum 2)) used))
            answer (some #(and (every? pentagonal? %) (last %)) test-nums)]
        (if answer
          answer
          (recur (conj used cur-sum) (rest nums)))))))

(deftest test1 (is (= (min-sum-diff-pentagonal) 5482660)))

(time (run-tests 'euler.volume_1.task44))

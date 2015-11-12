; Topic: elementary
; Idea: cycle is formed from remnants by modulo d

(ns
  ^{:author raptor_MVK}
  euler.volume_1.task26
  (:use clojure.test))

(defn max-recur-cycle
  "Given N, returns d < N, for which 1/d contains the longest recurring cycle in its
  decimal fraction part"
  [n]
  (let [recur-cycle (fn [d] (loop [cur 1
                                   res []]
                              (if (= cur 0)
                                0
                                (let [new-cur (* 10 cur)
                                      new-d (rem new-cur d)]
                                  (if (some #{new-d} (set res))
                                    (count (drop-while #(not (= % new-d)) res))
                                    (recur new-d (concat res [new-d])))))))]
    (+ (first (apply max-key second (map-indexed vector
                                      (pmap recur-cycle (range 2 n))))) 2)))


(deftest test1 (is (= (max-recur-cycle 10) 7)))

(deftest test2 (is (= (max-recur-cycle 1000) 983)))

(time (run-tests 'euler.volume_1.task26))

(shutdown-agents)
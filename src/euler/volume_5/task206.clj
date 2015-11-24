; Topic: elementary
; Idea: full search, the answer could start from 10, 11, 12 or 13
;       and could end with 00, 30 or 70

(ns
  ^{:author raptor_MVK}
  euler.volume_5.task206
  (:use clojure.test)
  (:use tools.math))

(defn find-square
  "Returns the unique positive integer whose square has the form 1_2_3_4_5_6_7_8_9_0,
  where each '_' is a single digit"
  []
  (let [affixes (for [prefix [10 11 12 13]
                      suffix [0 30 70]]
                  (vector prefix suffix))
        mult (nat-pow 10 8)
        expr #"1.2.3.4.5.6.7.8.9.0"]
    (loop [coll affixes]
      (let [cur (first coll)
            nums (map #(+ (* mult (first cur)) (* 100 %) (last cur)) (range 1000000))
            answer (filter #(re-find expr (last %)) (map #(vector % (str (* % %))) nums))]
        (if (empty? answer)
          (recur (rest coll))
          (first (first answer)))))))

(deftest test1 (is (= (find-square) 1389019170)))

(time (run-tests 'euler.volume_5.task206))

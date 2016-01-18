; Topic: elementary
; Idea: n-digit integer could be n-th power of one-digit number only

(ns
  ^{:author raptor_MVK}
  euler.volume_2.task63
  (:use clojure.test)
  (:use tools.math)
  (:use tools.conversions))

(defn n-digit-n-pow-count
  "Returns number of n-digit positive integers exist which are also an n-th power"
  []
  (let [digits (range 1 10)]
    (loop [res 9
           cur-pow 2]
      (let [n-digits-n-pow (filter #(= cur-pow (count %))
                             (map #(num2seq (nat-pow % cur-pow)) digits))]
        (if (empty? n-digits-n-pow)
          res
          (recur (+ res (count n-digits-n-pow)) (inc cur-pow)))))))

(deftest test1 (is (= (n-digit-n-pow-count) 49)))

(time (run-tests 'euler.volume_2.task63))

; Topic: elementary

(ns
  ^{:author "raptor_MVK"}
  euler.volume_1.task43
  (:use clojure.test)
  (:use clojure.set)
  (:use tools.conversions))

 (defn substring-divisible-pandigital-sum
  "Returns the sum of all 10-digit pandigital numbers, which satisfy the following:
  - d2d3d4 is divisible by 2
  - d3d4d5 is divisible by 3
  - d4d5d6 is divisible by 5
  - d5d6d7 is divisible by 7
  - d6d7d8 is divisible by 11
  - d7d8d9 is divisible by 13
  - d8d9d10 is divisible by 17
  where di - i-th digit of number"
  []
  (let [possible-digits #(clojure.set/difference (set (range 10)) (set %))
        add-digit (fn [coll] (map #(into coll [%]) (possible-digits coll)))
        add-prefix (fn [coll divider]
                     (filter #(= 0 (rem (seq2num (take 3 %)) divider))
                       (apply concat (map add-digit coll))))
        d8-10 (filter #(= 3 (count (distinct %)))
                (map num2seq (take-while #(< % 1000) (iterate #(+ % 17) 17))))
        d7-10 (add-prefix d8-10 13)
        d6-10 (add-prefix d7-10 11)
        d5-10 (add-prefix d6-10 7)
        d4-10 (add-prefix d5-10 5)
        d3-10 (add-prefix d4-10 3)
        d2-10 (add-prefix d3-10 2)]
    (reduce +' (map seq2num (apply concat (map add-digit d2-10))))))

(deftest test1 (is (= (substring-divisible-pandigital-sum) 16695334890)))

(time (run-tests 'euler.volume_1.task43))

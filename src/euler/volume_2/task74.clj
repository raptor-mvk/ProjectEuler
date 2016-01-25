; Topic: elementary

(ns
  ^{:author raptor_MVK}
  euler.volume_2.task74
  (:use clojure.test)
  (:use tools.math)
  (:use tools.conversions))

(defn factorial-chains-count
  "Given N, K, returns number of chains, produced by summation of factorials of digits,
   that contains exactly K non-repeating terms and starting with m < N"
  [n k]
  (let [make-chain (fn make-chain [n res]
                     (if (get-in res [n])
                       [n]
                       (let [next-n (reduce + (map fact (num2seq n)))]
                         (concat [n] (make-chain next-n res)))))
        add (fn [m k v] (assoc-in m [k] v))]
    (loop [i 3
           res {1 1, 2 1, 169 3, 363601 3, 1454 3, 145 1, 40585 1, 871 2, 45361 2,
                872 2, 45362 2}]
      (if (= i n)
        (count (filter #(= k (val %)) res))
        (let [chain (make-chain i res)
              chain-length (dec (+ (get-in res [(last chain)]) (count chain)))
              subchain-lengths (map #(vector %1 (- chain-length %2)) chain (range))]
          (recur (inc i)
            (reduce #(add %1 (first %2) (last %2)) res subchain-lengths)))))))

(deftest test1 (is (= (factorial-chains-count 1000000 60) 402)))

(time (run-tests 'euler.volume_2.task74))

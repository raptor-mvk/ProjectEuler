; Topic: elementary
; Idea: product could be extended to required length by multiplying by one

(ns
  ^{:author "raptor_MVK"}
  euler.volume_2.task88
  (:use clojure.test)
  (:use tools.factorization))

(defn sum-min-product-sums
  "Given N, returns the sum of all the minimal product-sum numbers for 2 <= k <= N"
  [n]
  (let [get-factors (fn [n] (filter #(<= % (int (Math/sqrt n))) (rest (factors n))))
        upd-res (fn [m k v] (update m k #(if (nil? %) v %)))
        upd-ans (fn [m k v] (update m k #(if (nil? %) #{v} (conj % v))))
        nums (loop [i 4
                    factors (get-factors i)
                    ans {}
                    res {}]
               (if (> i (* n 2))
                 res
                 (if (empty? factors)
                   (recur (inc i) (get-factors (inc i)) ans res)
                   (let [cur (first factors)
                         cur-quot (quot i cur)
                         quot-ks (get-in ans [cur-quot])
                         ks (concat [(+ (- i cur-quot cur) 2)]
                              (if quot-ks (map #(+ (- i cur-quot cur) % 1) quot-ks) []))]
                     (recur i (rest factors) (reduce #(upd-ans %1 i %2) ans ks)
                       (reduce #(upd-res %1 %2 i) res ks))))))]
    (reduce + (distinct (vals (filter #(<= (key %) n) nums))))))

(deftest test1 (is (= (sum-min-product-sums 6) 30)))

(deftest test2 (is (= (sum-min-product-sums 12) 61)))

(deftest test3 (is (= (sum-min-product-sums 12000) 7587457)))

(time (run-tests 'euler.volume_2.task88))

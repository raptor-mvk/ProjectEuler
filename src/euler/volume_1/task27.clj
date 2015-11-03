; Topic: elementary
; Idea: b should be prime, because for n = 0 formula turns out to b
;       a should has the same parity as b, because for n = 1 formula turns out to
;       a + b + 1

(ns
  ^{:author raptor_MVK}
  euler.volume_1.task27
  (:use clojure.test)
  (:use tools.factorization))

(defn max-quadratic-primes
  "Given K, M, returns product of coefficients a, b of formula n ^ 2 + a * n + b, which
  produces the maximum number of primes for consecutive values of n, where |a| < K,
  |b| < M"
  [k m]
  (let [quadratic-primes-count (fn [a b]
                                 (count (take-while #(and (> % 0) (prime? %))
                                          (map #(+ b (* a %) (* % %)) (range)))))
        b-primes (take-while #(< % m) (prime-seq))
        bs (concat b-primes (map - b-primes))
        make-as (fn [b] (map #(vector % b)
                          (filter #(even? (+ % b)) (range (inc (- k)) k))))
        pairs (apply concat (map make-as bs))
        answer (apply max-key last
                 (map #(into % [(apply quadratic-primes-count %)]) pairs))]
    (* (first answer) (second answer))))


(deftest test1 (is (= (max-quadratic-primes 1000 1000) -59231)))

(time (run-tests 'euler.volume_1.task27))

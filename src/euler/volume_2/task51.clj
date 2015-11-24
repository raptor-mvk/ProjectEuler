; Topic: number theory
; Idea: the answer has at least 5 digits with 3 changing digits
;       if the number of digits is not divisible by 3, then there
;       would be at least 3 numbers divisible by 3 in sequence

(ns
  ^{:author raptor_MVK}
  euler.volume_2.task51
  (:use clojure.test)
  (:use clojure.set)
  (:use tools.core)
  (:use tools.comb)
  (:use tools.math)
  (:use tools.factorization)
  (:use tools.conversions))

(defn eight-primes-family
  "Returns the smallest prime which, by replacing part of the number with the same
  digit, is part of an eight prime value family."
  []
  (let [start-n 4
        primes (drop-while #(< % (nat-pow 10 start-n)) (prime-seq))
        equal-3-digits? (fn [n] (>= (apply max (vals (frequencies (num2seq n)))) 3))
        make-cur-primes (fn [n next-primes]
                          (map num2seq
                            (filter equal-3-digits?
                              (take-while #(< % (nat-pow 10 (inc n))) next-primes))))
        make-next-primes (fn [n next-primes]
                           (drop-while #(< % (nat-pow 10 (inc n))) next-primes))
        make-digits (fn [n]
                      (distinct (map #(vector (set %)
                                        (clojure.set/difference (set (range+ n)) (set %)))
                                  (all-part-perms 3 (range+ n)))))
        extract-digits (fn [digits coll]
                         (map #(nth coll %) (sort (vec digits))))
        group-by-digits (fn [[equal-digits other-digits] coll]
                          (when (apply = (extract-digits equal-digits coll))
                            (extract-digits other-digits coll)))]
    (loop [n (inc start-n)
           digits (make-digits start-n)
           cur-primes (make-cur-primes start-n primes)
           next-primes (make-next-primes start-n primes)]
      (if (empty? digits)
        (recur (inc n) (make-digits n) (make-cur-primes n next-primes)
          (make-next-primes n next-primes))
        (let [grouped-primes (group-by #(group-by-digits (first digits) %) cur-primes)
              counted-groups (map #(vector (key %) (count (val %))) grouped-primes)
              answer (filter #(and (not (nil? (first %))) (= 8 (last %))) counted-groups)]
          (if (empty? answer)
            (recur n (rest digits) cur-primes next-primes)
            (apply min (map seq2num
                         (get-in grouped-primes [(first (first answer))])))))))))

(deftest test1 (is (= (eight-primes-family) 121313)))

(time (run-tests 'euler.volume_2.task51))

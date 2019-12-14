; Topic: elementary
; Idea: calculate sets of prime pairs, then intersect these sets until N primes will be
;       reached

(ns
  ^{:author "raptor_MVK"}
  euler.volume_2.task60
  (:use clojure.set)
  (:use tools.factorization)
  (:use clojure.test))

(defn min-pairwise-prime-set-sum
  "Given N, returns the lowest sum for a set of N primes with a property: by taking any
   two primes and concatenating them in any order the result will always be prime"
  [n]
  (let [make-cur-primes (fn [limit primes]
                          (take-while #(< % (* limit 10)) primes))
        make-next-primes (fn [limit primes]
                           (drop-while #(< % (* limit 10)) primes))
        concat-primes (fn [n n-limit m]
                        (+ (* m n-limit) n))
        check-pair (fn [[prime1 limit1] [prime2 limit2]]
                     (every? prime? [(concat-primes prime1 limit1 prime2)
                                     (concat-primes prime2 limit2 prime1)]))
        check-sets (fn check-sets [sets primes-set primes n]
                     (if (= n 2)
                       (if (empty? primes-set)
                         false
                         (concat primes [(apply min-key first primes-set)]))
                       (loop [cur-set primes-set]
                         (if (empty? cur-set)
                           false
                           (let [new-prime (first cur-set)
                                 new-set (intersection primes-set
                                           (get-in sets [new-prime]))]
                             (if (empty? new-set)
                               (recur (rest cur-set))
                               (if-let [answer (check-sets sets new-set
                                                 (concat primes [new-prime]) (dec n))]
                                 answer
                                 (recur (rest cur-set)))))))))
        upd (fn [sets n prime] (update sets prime conj n))
        add (fn [sets n] (assoc sets n #{}))
        sum-answer (fn [coll] (reduce #(+ %1 (first %2)) 0 coll))]
    (loop [cur-limit 10
           cur-primes (drop 3 (make-cur-primes 1 (prime-seq)))
           next-primes (make-next-primes 1 (prime-seq))
           res (hash-map [3 10] #{})]
      (if (empty? cur-primes)
        (let [answer (filter (complement false?)
                       (map #(check-sets res (get-in res [%]) [%] n) (keys res)))]
          (if (empty? answer)
            (recur (* cur-limit 10) (make-cur-primes cur-limit next-primes)
              (make-next-primes cur-limit next-primes) res)
            (apply min (map sum-answer answer))))
        (let [cur-prime-limit [(first cur-primes) cur-limit]
              pairs (filter #(check-pair cur-prime-limit %) (keys res))]
          (recur cur-limit (rest cur-primes) next-primes
            (add (reduce #(upd %1 cur-prime-limit %2) res pairs)
              cur-prime-limit)))))))

(deftest test1 (is (= (min-pairwise-prime-set-sum 4) 792)))

(deftest test2 (is (= (min-pairwise-prime-set-sum 5) 26033)))

(time (run-tests 'euler.volume_2.task60))

(ns
  ^{:author raptor_MVK}
  tools.conversions)

(defn num2seq
  "Given N, K, returns sequential digit representation of N in base K;
  given N, returns num2seq(N, 10);
  given N, K, M returns last M digits of num2seq(N, K)"
  ([n]
    (num2seq n 10))
  ([n k]
    (loop [val n
           res '()]
      (if (= val 0)
        res
        (recur (quot val k) (conj res (rem val k))))))
  ([n k m]
    (loop [val n
           i 0
           res '()]
      (if (= i m)
        res
        (recur (quot val k) (inc i) (conj res (rem val k)))))))

(defn is-palindrome?
  "Given N, returns true if it is a palindrome"
  [n]
  (let [seq-n (num2seq n)]
    (= seq-n (reverse seq-n))))

(defn numstr2seq
  "Given a string representation of decimal number, returns its sequential digit
  representation"
  [s]
  (map #(- (int %) (int \0)) s))

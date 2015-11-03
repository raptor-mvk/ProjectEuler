(ns
  ^{:author raptor_MVK}
  tools.conversions)

(declare is-palindrome? num2seq numstr2seq seq2num)

(defn is-palindrome?
  "Given N, returns true if it is a palindrome"
  [n]
  (let [seq-n (num2seq n)]
    (= seq-n (reverse seq-n))))

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

(defn numstr2seq
  "Given a string representation of decimal number, returns its sequential digit
  representation"
  [s]
  (map #(- (int %) (int \0)) s))

(defn seq2num
  "Given a digit sequence and base K, returns number, represented by sequence;
  given a digit sequence, returns seq2num(c,10)"
  ([c]
    (seq2num c 10))
  ([c k]
    (reduce #(+ (* k %1) %2) 0 c)))
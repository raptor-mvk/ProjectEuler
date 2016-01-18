(ns
  ^{:author raptor_MVK}
  tools.conversions)

(declare is-palindrome? num2rom num2seq numstr2seq rom2num seq2num)

(defn is-palindrome?
  "Given N, returns true if it is a palindrome"
  [n]
  (let [seq-n (num2seq n)]
    (= seq-n (reverse seq-n))))

(defn num2rom
  "Given N smaller than 5000, return the corresponding roman numeral"
  [n]
  (let [rom (fn [n is vs xs] (condp >= n
                            0 ""
                            3 (apply str (repeat n is))
                            4 (str is vs)
                            8 (apply str (concat [vs] (repeat (- n 5) is)))
                            9 (str is xs)))]
    (clojure.string/replace (apply str (map #(rom (- (int %1) (int \0)) %2 %3 %4)
                                         (format "%04d" n) "MCXI" "NDLV" "MMCX"))
      #"N" "MMM")))

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

(defn rom2num
  "Given a string, representing a roman number, returns this number"
  [s]
  (let [r (replace (zipmap "IVXLCDM" [1 5 10 50 100 500 1000]) s)]
    (+ (reduce + (map #(if (>= %1 %2) %1 (- %1)) r (rest r))) (last r))))

(defn seq2num
  "Given a digit sequence and base K, returns number, represented by sequence;
  given a digit sequence, returns seq2num(c,10)"
  ([c]
    (seq2num c 10))
  ([c k]
    (reduce #(+ (* k %1) %2) 0 c)))
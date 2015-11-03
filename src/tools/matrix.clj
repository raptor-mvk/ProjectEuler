(ns
  ^{:author raptor_MVK}
  tools.matrix)

(declare diagonalize get-diag get-ldiag transpose)

(defn diagonalize
  "Given matrix, retunrs a sequence of all its diagonal vectors"
  [m]
  (let [n (count m)
        k (count (first m))
        horiz-pts (map #(vector % 0) (range k))
        vert-pts (fn [x] (map #(vector x %) (range 1 n)))]
    (concat (map #(get-diag m n k (first %) (last %)) (concat horiz-pts (vert-pts 0)))
      (map #(get-ldiag m n (first %) (last %)) (concat horiz-pts (vert-pts (dec n)))))))

(defn get-diag
  "Given matrix, N, K, X, Y returns vector, containing right-bottom diagonal, started
  from (X,Y)"
  [m n k x y]
  (let [len (min (- n x) (- k y))
        get-elem (fn [z] (nth (nth m (+ y z)) (+ x z)))]
    (map get-elem (range len))))

(defn get-ldiag
  "Given matrix, N, X, Y returns vector, containing left-bottom diagonal, started from
  (X,Y)"
  [m n x y]
  (let [len (min (inc x) (- n y))
        get-elem (fn [z] (nth (nth m (+ y z)) (- x z)))]
    (map get-elem (range len))))

(defn transpose
  "Given matrix, returns transposed matrix"
  [m]
  (let [k (count (first m))]
    (map (fn [i] (map #(nth % i) m)) (range k))))


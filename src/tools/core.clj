(ns
  ^{:author raptor_MVK}
  tools.core)

(declare but-nth n-subseqs range+ rrange rrange+ switch take-drop-while)

(defn but-nth
  "Given collection and N, returns collection with nth element removed"
  [coll n]
  (concat (take n coll) (drop (inc n) coll)))

(defn n-subseqs
  "Given sequence and N, returns all subsequences of length N"
  [coll n]
  (partition n (apply interleave (take n (iterate rest coll)))))

(defn range+
  "Given N, returns (range (inc n));
  given K, N, returns (range k (inc n))"
  ([n]
    (range+ 0 n))
  ([k n]
    (range k (inc n))))

(defn rrange
  "Given N, returns (range 1 n);
  given no arguments returns (rest (range))"
  ([]
    (rest (range)))
  ([n]
    (range 1 n)))

(defn rrange+
  "Given N, returns (range 1 (inc n))"
  [n]
  (range 1 (inc n)))

(defn switch
  "Given N and a collection, returns collection with first N elements moved to the end"
  [n coll]
  (if (= n 1)
    (concat (rest coll) [(first coll)])
    (concat (drop n coll) (take n coll))))

(defn take-drop-while
  "Given pred, pred2 and coll, returns (take-while pred2 (drop-while pred coll))"
  [pred pred2 coll]
  (take-while pred2 (drop-while pred coll)))
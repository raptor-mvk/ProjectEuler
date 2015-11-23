(ns
  ^{:author raptor_MVK}
  tools.geom
  (:use tools.core)
  (:use tools.math))

(declare inside? v*)

(defn inside?
  "Given a sequence of points, representing polygon, and a point, returns true, if
 point is inside polygon, and false otherwise"
  [coll point]
  (let [make-vector (fn [x y] (map #(- %1 %2) x y))
        point-vectors (map #(make-vector % point) coll)]
    (apply = (map #(sgn (v* %1 %2)) point-vectors (switch 1 point-vectors)))))

(defn v*
  "Given two vectors (A,B) and (C,D), returns their cross product"
  [[a b :as v] [c d :as w]]
  (- (* a d) (* b c)))

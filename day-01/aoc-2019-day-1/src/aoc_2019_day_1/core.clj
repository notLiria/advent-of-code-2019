(ns aoc-2019-day-1.core
  (:gen-class))

(def mass-file "masses.txt")

(defn str->int
  [str]
  (Integer. str))

(defn parse
  "Converts string into a nested array"
  [string]
  (clojure.string/split string #"\r\n"))

(defn masses-file-to-ints
  "Converts the masses text file to integers"
  [file]
  (map str->int (parse (slurp file))))

(defn calc-fuel
  "Calculates fuel mass based on mass
  Formula is floor(mass/3) - 2 "
  [mass]
  (- (quot mass 3) 2))

(defn calc-fuel-fuel
  "Calculates that the fuel that the fuel will use"
  [fuel-mass]
  (loop [sum 0 
         fm fuel-mass]
    (if (> 0 fm)
      sum
      (recur (+ sum fm) (calc-fuel fm)))))

(defn calc-all-ship-fuel-req
  "Calculates fuel mass of all ships"
  [masses]
  (map calc-fuel  masses))

(defn calc-all-fuel-fuel
  "Calculates the fuel that the fuel will use"
  [fuel-masses]
  (map calc-fuel-fuel fuel-masses))

(defn -main
  [& args]
  (println (reduce +  (calc-all-ship-fuel-req (masses-file-to-ints mass-file)))))


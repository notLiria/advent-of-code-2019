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

(defn calc-single-ship-fuel-req
  "Calculates fuel mass based on mass of ship. 
  Formula is floor(mass/3) - 2 "
  [mass]
  (- (quot mass 3) 2))

(defn calc-fuel-usage-fuel
  "Calculates the fuel that the fuel would use and so on.
  Mass: The mass of the ship"
  [mass]
  (let [fuel-mass (calc-single-ship-fuel-req mass)]
    (cond 
      (< 0 fuel-mass) (+ fuel-mass (calc-fuel-usage-fuel fuel-mass))
      :else mass)))

(defn calc-all-ship-fuel-req
  "Calculates fuel mass of all ships"
  [masses]
  (map calc-single-ship-fuel-req  masses))

(defn -main
  [& args]
  (println (reduce +  (calc-all-ship-fuel-req (masses-file-to-ints mass-file)))))


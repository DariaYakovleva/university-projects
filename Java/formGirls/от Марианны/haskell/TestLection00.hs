{-# LANGUAGE NoImplicitPrelude #-}

module TestLection00 where

data Bool = True | False
    deriving (Show, Eq, Ord)

f' x = x + 1 -- f = \x -> x + 1

sum x y = x + y -- sum = \x -> (\y -> x + y )

not :: Bool -> Bool
not x = if x == True then False else True
not x = case x of
            True -> False
            False -> True

not x | x == True = False
      | x == False = True
      | True = error "Ololo, such wow!"

not True = False
not False = True

sum3 x y z = x + sumYZ
        where
            sumYZ = sum y z

sum3 x y z = let sumYZ = sum y z in
                x + sumYZ

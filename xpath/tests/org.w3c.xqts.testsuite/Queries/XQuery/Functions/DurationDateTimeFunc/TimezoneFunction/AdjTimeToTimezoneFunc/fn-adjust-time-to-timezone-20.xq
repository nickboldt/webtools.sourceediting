(:*******************************************************:)
(:Test: adjust-time-to-timezone-20                       :)
(:Written By: Carmelo Montanez                           :)
(:Date: August 10, 2005                                  :)
(:Test Description: Evaluates The "adjust-time-to-timezone" function   :)
(:as part of a comparisson expression (ge operator).     :)
(:Use zulu timezone and empty sequence for 2nd argument. :)
(:*******************************************************:)

fn:adjust-time-to-timezone(xs:time("10:00:00Z"),()) ge fn:adjust-time-to-timezone(xs:time("11:00:00Z"),())
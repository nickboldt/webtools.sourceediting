(:*******************************************************:)
(: Test: K-SeqAVGFunc-21                                 :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: A test whose essence is: `string(avg((1, 2, 3, xs:double("NaN")))) eq "NaN"`. :)
(:*******************************************************:)
string(avg((1, 2, 3, xs:double("NaN")))) eq "NaN"
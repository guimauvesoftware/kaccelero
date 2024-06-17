package dev.kaccelero.commons.localization

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.cstr
import platform.Foundation.NSBundle
import platform.Foundation.NSString
import platform.Foundation.NSURL
import platform.Foundation.stringWithFormat

class TranslateFromTableUseCase(
    private val tableName: String = "Localizable",
) : ITranslateUseCase {

    @OptIn(ExperimentalForeignApi::class)
    override fun invoke(input1: Locale, input2: String, input3: List<String>): String {
        val localizedString = NSBundle.mainBundle
            .localizedStringForKey(input2, input2, tableName)
            .takeIf { it != input2 }
            ?: NSBundle.mainBundle.pathForResource("Base", "lproj")
                ?.let { NSURL(fileURLWithPath = it) }
                ?.let { NSBundle(it) }
                ?.localizedStringForKey(input2, input2, tableName)
            ?: input2

        // We cannot use *input3.map { it.cstr }.toTypedArray() because it is not supported in Kotlin/Native:
        // `When calling variadic Objective-C methods spread operator is supported only for *arrayOf(...)`
        return when (input3.size) {
            0 -> NSString.stringWithFormat(localizedString)
            1 -> NSString.stringWithFormat(localizedString, input3[0].cstr)
            2 -> NSString.stringWithFormat(localizedString, input3[0].cstr, input3[1].cstr)
            3 -> NSString.stringWithFormat(localizedString, input3[0].cstr, input3[1].cstr, input3[2].cstr)
            4 -> NSString.stringWithFormat(
                localizedString,
                input3[0].cstr,
                input3[1].cstr,
                input3[2].cstr,
                input3[3].cstr
            )

            5 -> NSString.stringWithFormat(
                localizedString,
                input3[0].cstr,
                input3[1].cstr,
                input3[2].cstr,
                input3[3].cstr,
                input3[4].cstr
            )

            6 -> NSString.stringWithFormat(
                localizedString,
                input3[0].cstr,
                input3[1].cstr,
                input3[2].cstr,
                input3[3].cstr,
                input3[4].cstr,
                input3[5].cstr
            )

            7 -> NSString.stringWithFormat(
                localizedString,
                input3[0].cstr,
                input3[1].cstr,
                input3[2].cstr,
                input3[3].cstr,
                input3[4].cstr,
                input3[5].cstr,
                input3[6].cstr
            )

            8 -> NSString.stringWithFormat(
                localizedString,
                input3[0].cstr,
                input3[1].cstr,
                input3[2].cstr,
                input3[3].cstr,
                input3[4].cstr,
                input3[5].cstr,
                input3[6].cstr,
                input3[7].cstr
            )

            9 -> NSString.stringWithFormat(
                localizedString,
                input3[0].cstr,
                input3[1].cstr,
                input3[2].cstr,
                input3[3].cstr,
                input3[4].cstr,
                input3[5].cstr,
                input3[6].cstr,
                input3[7].cstr,
                input3[8].cstr
            )

            else -> error("Too many arguments.")
        }
    }

}

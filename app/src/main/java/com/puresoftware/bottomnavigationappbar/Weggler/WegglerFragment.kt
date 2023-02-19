package com.puresoftware.bottomnavigationappbar.Weggler

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.marginTop
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.tabs.TabLayout
import com.puresoftware.bottomnavigationappbar.MainActivity
import com.puresoftware.bottomnavigationappbar.R
import com.puresoftware.bottomnavigationappbar.Weggler.MidFragment.ChallengeFragment
import com.puresoftware.bottomnavigationappbar.Weggler.MidFragment.CommunityFragment
import com.puresoftware.bottomnavigationappbar.Weggler.MidFragment.FeedFragment
import com.puresoftware.bottomnavigationappbar.Weggler.MidFragment.RankingFragment
import com.puresoftware.bottomnavigationappbar.databinding.WegglerFragmentBinding


class WegglerFragment : Fragment() {
    private var _binding : WegglerFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainActivity:MainActivity
    private lateinit var fm : FragmentManager

    private var feedFragment: FeedFragment? = null
    private var challengeFragment: ChallengeFragment? = null
    private var communityFragment: CommunityFragment? = null
    private var rankingFragment: RankingFragment? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity  = context as MainActivity
        fm = mainActivity.supportFragmentManager
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = WegglerFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    @SuppressLint("CommitPrefEdits")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //임의로 토큰 저장/// -나중에 삭제
        val sp  = mainActivity.getSharedPreferences("accessToken",Context.MODE_PRIVATE)
        val editor = sp.edit()
        val token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0X3VzZXIiLCJpc3MiOiJkZXYtYXBpLmtvb3J1LmJlIiwiaWF0IjoxNjc2ODAyODQ4LCJleHAiOjE2NzY4ODkyNDh9.Ts5V2sfscNkPf4_X68sZDb03_Ci_WMJBDx5OXX36xl5LlPsqur8PArNhS0OL9XkoI1m_I28PQ36V8UzDY3T2Rg"
        editor.putString("accessToken",token)
        editor.apply()
        /////////////////

        feedFragment = FeedFragment()
        challengeFragment = ChallengeFragment()
        communityFragment = CommunityFragment()
        rankingFragment = RankingFragment()

        midFragmentChange(feedFragment!!)
        binding.wegglerTab.addOnTabSelectedListener(object :  TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position){
                    0 ->{midFragmentChange(feedFragment!!)}
                    1 ->{midFragmentChange(challengeFragment!!)}
                    2 ->{midFragmentChange(communityFragment!!)}
                    3 ->{midFragmentChange(rankingFragment!!)}
                    else ->{midFragmentChange(feedFragment!!)}
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}

        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        feedFragment = null
        challengeFragment = null
        communityFragment = null
        rankingFragment = null
    }

    private fun midFragmentChange(goFragment:Fragment){
        fm.beginTransaction().replace(R.id.mid_container,goFragment)
            .commit()
    }

}
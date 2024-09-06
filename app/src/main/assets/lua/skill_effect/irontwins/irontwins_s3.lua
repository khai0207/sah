--v3
--[[
irontwins
1
0
penxue
2
{delay,qianjin,0,0,0,4,0.000000}{default,gongji4,1,0,5,45,1.000000}
1
{default,0.000000,0.000000,-200.000000,0.000000,0,1,0,4,0.000000,0,0,0,0}
0

0

1
{0,irontwins_gongji4_chongjibo,0,17,120.000000,-40.000000,9,0}
1
{default,impact,1,0,0,9,1.000000}
0

0

0


daiji
]]--
local skillTest = {
attack_type       = "yuangong",
start_p           = {2,0,0},
end_p             = {2,0,0},
anim_sequence    = {{"qianjin",0.000000,0,4,0},{"gongji4",1.000000,5,45,1}}, 
pos_sequence      = {{0.000000,0.000000,-200.000000,0.000000,0,1,0,4,0.000000,0,0,0,0}}, 
scale_sequence     = {}, 
fade_sequence      = {}, 
other_sequence      = {{"irontwins_gongji4_chongjibo",0,17,120.000000,-40.000000,{{"impact",1.000000,0,9,1}},{},{},{},0}}, 
bloodNum          = 5,
flyAnim           = "ladeng",
flyAnim_equence   = {{"impact",1}}, 
par               = "",
fly_v             = 100,
fly_h             = 0,
hurtAnim          = "penxue",
hurtAnim_equence  = {{"impact",1}},
skillCallFunc     = function(self,battleSkill,battleTable) 
   local skillData = battleTable.m_currentFrameData; 
   table.foreach(skillData,function(k,v) 
   end); 
   local hpValue = 1;
   local function attackEnd()
   end
   battleSkill:createNormalAttack({skills_table = self,hpValue = skillData.hurt,animEndCallFunc = attackEnd});
end, 
}
return skillTest
